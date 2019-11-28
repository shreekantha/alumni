package com.heraizen.dhi.alumni.service;

import com.heraizen.dhi.alumni.service.dto.AlumniDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.heraizen.dhi.alumni.domain.Alumni}.
 */
public interface AlumniService {

    /**
     * Save a alumni.
     *
     * @param alumniDTO the entity to save.
     * @return the persisted entity.
     */
    AlumniDTO save(AlumniDTO alumniDTO);

    /**
     * Get all the alumni.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AlumniDTO> findAll(Pageable pageable);

    /**
     * Get all the alumni.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AlumniDTO> findAll(String position, Double experience, String yearOfGraduation, String department,
	    Pageable pageable);

    /**
     * Get the "id" alumni.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AlumniDTO> findOne(String id);

    /**
     * Delete the "id" alumni.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
