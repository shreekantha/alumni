package com.heraizen.dhi.alumni.service;

import com.heraizen.dhi.alumni.service.dto.ReqOrClaimSubjectDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.heraizen.dhi.alumni.domain.ReqOrClaimSubject}.
 */
public interface ReqOrClaimSubjectService {

    /**
     * Save a reqOrClaimSubject.
     *
     * @param reqOrClaimSubjectDTO the entity to save.
     * @return the persisted entity.
     */
    ReqOrClaimSubjectDTO save(ReqOrClaimSubjectDTO reqOrClaimSubjectDTO);

    /**
     * Get all the reqOrClaimSubjects.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReqOrClaimSubjectDTO> findAll(Pageable pageable);


    /**
     * Get the "id" reqOrClaimSubject.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReqOrClaimSubjectDTO> findOne(String id);

    /**
     * Delete the "id" reqOrClaimSubject.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
