package com.heraizen.dhi.alumni.service;

import com.heraizen.dhi.alumni.service.dto.ProfDevServiceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.heraizen.dhi.alumni.domain.ProfDevService}.
 */
public interface ProfDevServiceService {

    /**
     * Save a profDevService.
     *
     * @param profDevServiceDTO the entity to save.
     * @return the persisted entity.
     */
    ProfDevServiceDTO save(ProfDevServiceDTO profDevServiceDTO);

    /**
     * Get all the profDevServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProfDevServiceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" profDevService.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProfDevServiceDTO> findOne(String id);

    /**
     * Delete the "id" profDevService.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
