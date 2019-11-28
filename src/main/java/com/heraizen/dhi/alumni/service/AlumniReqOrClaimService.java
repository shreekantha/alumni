package com.heraizen.dhi.alumni.service;

import com.heraizen.dhi.alumni.service.dto.AlumniReqOrClaimDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.heraizen.dhi.alumni.domain.AlumniReqOrClaim}.
 */
public interface AlumniReqOrClaimService {

    /**
     * Save a alumniReqOrClaim.
     *
     * @param alumniReqOrClaimDTO the entity to save.
     * @return the persisted entity.
     */
    AlumniReqOrClaimDTO save(AlumniReqOrClaimDTO alumniReqOrClaimDTO);

    /**
     * Get all the alumniReqOrClaims.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AlumniReqOrClaimDTO> findAll(Pageable pageable);


    /**
     * Get the "id" alumniReqOrClaim.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AlumniReqOrClaimDTO> findOne(String id);

    /**
     * Delete the "id" alumniReqOrClaim.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    Page<AlumniReqOrClaimDTO> findAlumniReqOrClaims(String assignee, String requestedBy, Pageable pageable);
}
