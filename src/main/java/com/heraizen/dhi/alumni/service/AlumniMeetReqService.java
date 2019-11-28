package com.heraizen.dhi.alumni.service;

import com.heraizen.dhi.alumni.service.dto.AlumniMeetReqDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.heraizen.dhi.alumni.domain.AlumniMeetReq}.
 */
public interface AlumniMeetReqService {

    /**
     * Save a alumniMeetReq.
     *
     * @param alumniMeetReqDTO the entity to save.
     * @return the persisted entity.
     */
    AlumniMeetReqDTO save(AlumniMeetReqDTO alumniMeetReqDTO);

    /**
     * Get all the alumniMeetReqs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AlumniMeetReqDTO> findAll(Pageable pageable);


    /**
     * Get the "id" alumniMeetReq.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AlumniMeetReqDTO> findOne(String id);

    /**
     * Delete the "id" alumniMeetReq.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    Page<AlumniMeetReqDTO> findAlumniMeetRequests(String requestedBy, String requestedTo, Pageable pageable);
}
