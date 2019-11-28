package com.heraizen.dhi.alumni.service;

import com.heraizen.dhi.alumni.service.dto.PdsCourseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.heraizen.dhi.alumni.domain.PdsCourse}.
 */
public interface PdsCourseService {

    /**
     * Save a pdsCourse.
     *
     * @param pdsCourseDTO the entity to save.
     * @return the persisted entity.
     */
    PdsCourseDTO save(PdsCourseDTO pdsCourseDTO);

    /**
     * Get all the pdsCourses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PdsCourseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" pdsCourse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PdsCourseDTO> findOne(String id);

    /**
     * Delete the "id" pdsCourse.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
