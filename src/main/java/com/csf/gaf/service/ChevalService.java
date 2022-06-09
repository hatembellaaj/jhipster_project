package com.csf.gaf.service;

import com.csf.gaf.domain.Cheval;
import com.csf.gaf.repository.ChevalRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Cheval}.
 */
@Service
@Transactional
public class ChevalService {

    private final Logger log = LoggerFactory.getLogger(ChevalService.class);

    private final ChevalRepository chevalRepository;

    public ChevalService(ChevalRepository chevalRepository) {
        this.chevalRepository = chevalRepository;
    }

    /**
     * Save a cheval.
     *
     * @param cheval the entity to save.
     * @return the persisted entity.
     */
    public Cheval save(Cheval cheval) {
        log.debug("Request to save Cheval : {}", cheval);
        return chevalRepository.save(cheval);
    }

    /**
     * Update a cheval.
     *
     * @param cheval the entity to save.
     * @return the persisted entity.
     */
    public Cheval update(Cheval cheval) {
        log.debug("Request to save Cheval : {}", cheval);
        return chevalRepository.save(cheval);
    }

    /**
     * Partially update a cheval.
     *
     * @param cheval the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Cheval> partialUpdate(Cheval cheval) {
        log.debug("Request to partially update Cheval : {}", cheval);

        return chevalRepository
            .findById(cheval.getId())
            .map(existingCheval -> {
                if (cheval.getName() != null) {
                    existingCheval.setName(cheval.getName());
                }
                if (cheval.getWeight() != null) {
                    existingCheval.setWeight(cheval.getWeight());
                }

                return existingCheval;
            })
            .map(chevalRepository::save);
    }

    /**
     * Get all the chevals.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Cheval> findAll() {
        log.debug("Request to get all Chevals");
        return chevalRepository.findAll();
    }

    /**
     * Get one cheval by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Cheval> findOne(Long id) {
        log.debug("Request to get Cheval : {}", id);
        return chevalRepository.findById(id);
    }

    /**
     * Delete the cheval by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Cheval : {}", id);
        chevalRepository.deleteById(id);
    }
}
