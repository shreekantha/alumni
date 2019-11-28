package com.heraizen.dhi.alumni.service;

import com.heraizen.dhi.alumni.service.dto.PdsCourseTopicDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.heraizen.dhi.alumni.domain.PdsCourseTopic}.
 */
public interface PdsCourseTopicService {

    /**
     * Save a pdsCourseTopic.
     *
     * @param pdsCourseTopicDTO the entity to save.
     * @return the persisted entity.
     */
    PdsCourseTopicDTO save(PdsCourseTopicDTO pdsCourseTopicDTO);

    /**
     * Get all the pdsCourseTopics.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PdsCourseTopicDTO> findAll(Pageable pageable);


    /**
     * Get the "id" pdsCourseTopic.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PdsCourseTopicDTO> findOne(String id);

    /**
     * Delete the "id" pdsCourseTopic.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
