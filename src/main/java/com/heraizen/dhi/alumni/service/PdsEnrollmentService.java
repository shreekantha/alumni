package com.heraizen.dhi.alumni.service;

import com.heraizen.dhi.alumni.service.dto.PdsEnrollmentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.heraizen.dhi.alumni.domain.PdsEnrollment}.
 */
public interface PdsEnrollmentService {

    /**
     * Save a pdsEnrollment.
     *
     * @param pdsEnrollmentDTO the entity to save.
     * @return the persisted entity.
     */
    PdsEnrollmentDTO save(PdsEnrollmentDTO pdsEnrollmentDTO);

    /**
     * Get all the pdsEnrollments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PdsEnrollmentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" pdsEnrollment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PdsEnrollmentDTO> findOne(String id);

    /**
     * Delete the "id" pdsEnrollment.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
