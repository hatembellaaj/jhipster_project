package com.csf.gaf.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.csf.gaf.IntegrationTest;
import com.csf.gaf.domain.Cheval;
import com.csf.gaf.repository.ChevalRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ChevalResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ChevalResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Float DEFAULT_WEIGHT = 1F;
    private static final Float UPDATED_WEIGHT = 2F;

    private static final String ENTITY_API_URL = "/api/chevals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ChevalRepository chevalRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChevalMockMvc;

    private Cheval cheval;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cheval createEntity(EntityManager em) {
        Cheval cheval = new Cheval().name(DEFAULT_NAME).weight(DEFAULT_WEIGHT);
        return cheval;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cheval createUpdatedEntity(EntityManager em) {
        Cheval cheval = new Cheval().name(UPDATED_NAME).weight(UPDATED_WEIGHT);
        return cheval;
    }

    @BeforeEach
    public void initTest() {
        cheval = createEntity(em);
    }

    @Test
    @Transactional
    void createCheval() throws Exception {
        int databaseSizeBeforeCreate = chevalRepository.findAll().size();
        // Create the Cheval
        restChevalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cheval)))
            .andExpect(status().isCreated());

        // Validate the Cheval in the database
        List<Cheval> chevalList = chevalRepository.findAll();
        assertThat(chevalList).hasSize(databaseSizeBeforeCreate + 1);
        Cheval testCheval = chevalList.get(chevalList.size() - 1);
        assertThat(testCheval.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCheval.getWeight()).isEqualTo(DEFAULT_WEIGHT);
    }

    @Test
    @Transactional
    void createChevalWithExistingId() throws Exception {
        // Create the Cheval with an existing ID
        cheval.setId(1L);

        int databaseSizeBeforeCreate = chevalRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restChevalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cheval)))
            .andExpect(status().isBadRequest());

        // Validate the Cheval in the database
        List<Cheval> chevalList = chevalRepository.findAll();
        assertThat(chevalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllChevals() throws Exception {
        // Initialize the database
        chevalRepository.saveAndFlush(cheval);

        // Get all the chevalList
        restChevalMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cheval.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())));
    }

    @Test
    @Transactional
    void getCheval() throws Exception {
        // Initialize the database
        chevalRepository.saveAndFlush(cheval);

        // Get the cheval
        restChevalMockMvc
            .perform(get(ENTITY_API_URL_ID, cheval.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cheval.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingCheval() throws Exception {
        // Get the cheval
        restChevalMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCheval() throws Exception {
        // Initialize the database
        chevalRepository.saveAndFlush(cheval);

        int databaseSizeBeforeUpdate = chevalRepository.findAll().size();

        // Update the cheval
        Cheval updatedCheval = chevalRepository.findById(cheval.getId()).get();
        // Disconnect from session so that the updates on updatedCheval are not directly saved in db
        em.detach(updatedCheval);
        updatedCheval.name(UPDATED_NAME).weight(UPDATED_WEIGHT);

        restChevalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCheval.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCheval))
            )
            .andExpect(status().isOk());

        // Validate the Cheval in the database
        List<Cheval> chevalList = chevalRepository.findAll();
        assertThat(chevalList).hasSize(databaseSizeBeforeUpdate);
        Cheval testCheval = chevalList.get(chevalList.size() - 1);
        assertThat(testCheval.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCheval.getWeight()).isEqualTo(UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    void putNonExistingCheval() throws Exception {
        int databaseSizeBeforeUpdate = chevalRepository.findAll().size();
        cheval.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChevalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cheval.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cheval))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cheval in the database
        List<Cheval> chevalList = chevalRepository.findAll();
        assertThat(chevalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCheval() throws Exception {
        int databaseSizeBeforeUpdate = chevalRepository.findAll().size();
        cheval.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChevalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cheval))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cheval in the database
        List<Cheval> chevalList = chevalRepository.findAll();
        assertThat(chevalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCheval() throws Exception {
        int databaseSizeBeforeUpdate = chevalRepository.findAll().size();
        cheval.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChevalMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cheval)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cheval in the database
        List<Cheval> chevalList = chevalRepository.findAll();
        assertThat(chevalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateChevalWithPatch() throws Exception {
        // Initialize the database
        chevalRepository.saveAndFlush(cheval);

        int databaseSizeBeforeUpdate = chevalRepository.findAll().size();

        // Update the cheval using partial update
        Cheval partialUpdatedCheval = new Cheval();
        partialUpdatedCheval.setId(cheval.getId());

        partialUpdatedCheval.name(UPDATED_NAME).weight(UPDATED_WEIGHT);

        restChevalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCheval.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCheval))
            )
            .andExpect(status().isOk());

        // Validate the Cheval in the database
        List<Cheval> chevalList = chevalRepository.findAll();
        assertThat(chevalList).hasSize(databaseSizeBeforeUpdate);
        Cheval testCheval = chevalList.get(chevalList.size() - 1);
        assertThat(testCheval.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCheval.getWeight()).isEqualTo(UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    void fullUpdateChevalWithPatch() throws Exception {
        // Initialize the database
        chevalRepository.saveAndFlush(cheval);

        int databaseSizeBeforeUpdate = chevalRepository.findAll().size();

        // Update the cheval using partial update
        Cheval partialUpdatedCheval = new Cheval();
        partialUpdatedCheval.setId(cheval.getId());

        partialUpdatedCheval.name(UPDATED_NAME).weight(UPDATED_WEIGHT);

        restChevalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCheval.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCheval))
            )
            .andExpect(status().isOk());

        // Validate the Cheval in the database
        List<Cheval> chevalList = chevalRepository.findAll();
        assertThat(chevalList).hasSize(databaseSizeBeforeUpdate);
        Cheval testCheval = chevalList.get(chevalList.size() - 1);
        assertThat(testCheval.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCheval.getWeight()).isEqualTo(UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    void patchNonExistingCheval() throws Exception {
        int databaseSizeBeforeUpdate = chevalRepository.findAll().size();
        cheval.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChevalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cheval.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cheval))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cheval in the database
        List<Cheval> chevalList = chevalRepository.findAll();
        assertThat(chevalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCheval() throws Exception {
        int databaseSizeBeforeUpdate = chevalRepository.findAll().size();
        cheval.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChevalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cheval))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cheval in the database
        List<Cheval> chevalList = chevalRepository.findAll();
        assertThat(chevalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCheval() throws Exception {
        int databaseSizeBeforeUpdate = chevalRepository.findAll().size();
        cheval.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChevalMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(cheval)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cheval in the database
        List<Cheval> chevalList = chevalRepository.findAll();
        assertThat(chevalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCheval() throws Exception {
        // Initialize the database
        chevalRepository.saveAndFlush(cheval);

        int databaseSizeBeforeDelete = chevalRepository.findAll().size();

        // Delete the cheval
        restChevalMockMvc
            .perform(delete(ENTITY_API_URL_ID, cheval.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cheval> chevalList = chevalRepository.findAll();
        assertThat(chevalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
