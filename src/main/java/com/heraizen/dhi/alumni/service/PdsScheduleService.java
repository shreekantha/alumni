package com.heraizen.dhi.alumni.service;

import com.heraizen.dhi.alumni.service.dto.PdsScheduleDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.heraizen.dhi.alumni.domain.PdsSchedule}.
 */
public interface PdsScheduleService {

    /**
     * Save a pdsSchedule.
     *
     * @param pdsScheduleDTO the entity to save.
     * @return the persisted entity.
     */
    PdsScheduleDTO save(PdsScheduleDTO pdsScheduleDTO);

    /**
     * Get all the pdsSchedules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PdsScheduleDTO> findAll(Pageable pageable);

    /**
     * Get all the pdsSchedules with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<PdsScheduleDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" pdsSchedule.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PdsScheduleDTO> findOne(String id);

    /**
     * Delete the "id" pdsSchedule.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
