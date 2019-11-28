package com.heraizen.dhi.alumni.service;

import com.heraizen.dhi.alumni.service.dto.MeetReqTopicDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.heraizen.dhi.alumni.domain.MeetReqTopic}.
 */
public interface MeetReqTopicService {

    /**
     * Save a meetReqTopic.
     *
     * @param meetReqTopicDTO the entity to save.
     * @return the persisted entity.
     */
    MeetReqTopicDTO save(MeetReqTopicDTO meetReqTopicDTO);

    /**
     * Get all the meetReqTopics.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MeetReqTopicDTO> findAll(Pageable pageable);


    /**
     * Get the "id" meetReqTopic.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MeetReqTopicDTO> findOne(String id);

    /**
     * Delete the "id" meetReqTopic.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
