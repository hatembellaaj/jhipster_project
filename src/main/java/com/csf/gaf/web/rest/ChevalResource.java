package com.csf.gaf.web.rest;

import com.csf.gaf.domain.Cheval;
import com.csf.gaf.repository.ChevalRepository;
import com.csf.gaf.service.ChevalService;
import com.csf.gaf.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.csf.gaf.domain.Cheval}.
 */
@RestController
@RequestMapping("/api")
public class ChevalResource {

    private final Logger log = LoggerFactory.getLogger(ChevalResource.class);

    private static final String ENTITY_NAME = "gafCheval";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChevalService chevalService;

    private final ChevalRepository chevalRepository;

    public ChevalResource(ChevalService chevalService, ChevalRepository chevalRepository) {
        this.chevalService = chevalService;
        this.chevalRepository = chevalRepository;
    }

    /**
     * {@code POST  /chevals} : Create a new cheval.
     *
     * @param cheval the cheval to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cheval, or with status {@code 400 (Bad Request)} if the cheval has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/chevals")
    public ResponseEntity<Cheval> createCheval(@RequestBody Cheval cheval) throws URISyntaxException {
        log.debug("REST request to save Cheval : {}", cheval);
        if (cheval.getId() != null) {
            throw new BadRequestAlertException("A new cheval cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Cheval result = chevalService.save(cheval);
        return ResponseEntity
            .created(new URI("/api/chevals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /chevals/:id} : Updates an existing cheval.
     *
     * @param id the id of the cheval to save.
     * @param cheval the cheval to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cheval,
     * or with status {@code 400 (Bad Request)} if the cheval is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cheval couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/chevals/{id}")
    public ResponseEntity<Cheval> updateCheval(@PathVariable(value = "id", required = false) final Long id, @RequestBody Cheval cheval)
        throws URISyntaxException {
        log.debug("REST request to update Cheval : {}, {}", id, cheval);
        if (cheval.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cheval.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chevalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Cheval result = chevalService.update(cheval);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cheval.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /chevals/:id} : Partial updates given fields of an existing cheval, field will ignore if it is null
     *
     * @param id the id of the cheval to save.
     * @param cheval the cheval to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cheval,
     * or with status {@code 400 (Bad Request)} if the cheval is not valid,
     * or with status {@code 404 (Not Found)} if the cheval is not found,
     * or with status {@code 500 (Internal Server Error)} if the cheval couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/chevals/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Cheval> partialUpdateCheval(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Cheval cheval
    ) throws URISyntaxException {
        log.debug("REST request to partial update Cheval partially : {}, {}", id, cheval);
        if (cheval.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cheval.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chevalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Cheval> result = chevalService.partialUpdate(cheval);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cheval.getId().toString())
        );
    }

    /**
     * {@code GET  /chevals} : get all the chevals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chevals in body.
     */
    @GetMapping("/chevals")
    public List<Cheval> getAllChevals() {
        log.debug("REST request to get all Chevals");
        return chevalService.findAll();
    }

    /**
     * {@code GET  /chevals/:id} : get the "id" cheval.
     *
     * @param id the id of the cheval to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cheval, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chevals/{id}")
    public ResponseEntity<Cheval> getCheval(@PathVariable Long id) {
        log.debug("REST request to get Cheval : {}", id);
        Optional<Cheval> cheval = chevalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cheval);
    }

    /**
     * {@code DELETE  /chevals/:id} : delete the "id" cheval.
     *
     * @param id the id of the cheval to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chevals/{id}")
    public ResponseEntity<Void> deleteCheval(@PathVariable Long id) {
        log.debug("REST request to delete Cheval : {}", id);
        chevalService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
