package com.heraizen.dhi.alumni.service.impl;

import com.heraizen.dhi.alumni.service.PdsCourseTopicService;
import com.heraizen.dhi.alumni.domain.PdsCourseTopic;
import com.heraizen.dhi.alumni.repository.PdsCourseTopicRepository;
import com.heraizen.dhi.alumni.service.dto.PdsCourseTopicDTO;
import com.heraizen.dhi.alumni.service.mapper.PdsCourseTopicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PdsCourseTopic}.
 */
@Service
public class PdsCourseTopicServiceImpl implements PdsCourseTopicService {

    private final Logger log = LoggerFactory.getLogger(PdsCourseTopicServiceImpl.class);

    private final PdsCourseTopicRepository pdsCourseTopicRepository;

    private final PdsCourseTopicMapper pdsCourseTopicMapper;

    public PdsCourseTopicServiceImpl(PdsCourseTopicRepository pdsCourseTopicRepository, PdsCourseTopicMapper pdsCourseTopicMapper) {
        this.pdsCourseTopicRepository = pdsCourseTopicRepository;
        this.pdsCourseTopicMapper = pdsCourseTopicMapper;
    }

    /**
     * Save a pdsCourseTopic.
     *
     * @param pdsCourseTopicDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PdsCourseTopicDTO save(PdsCourseTopicDTO pdsCourseTopicDTO) {
        log.debug("Request to save PdsCourseTopic : {}", pdsCourseTopicDTO);
        PdsCourseTopic pdsCourseTopic = pdsCourseTopicMapper.toEntity(pdsCourseTopicDTO);
        pdsCourseTopic = pdsCourseTopicRepository.save(pdsCourseTopic);
        return pdsCourseTopicMapper.toDto(pdsCourseTopic);
    }

    /**
     * Get all the pdsCourseTopics.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<PdsCourseTopicDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PdsCourseTopics");
        return pdsCourseTopicRepository.findAll(pageable)
            .map(pdsCourseTopicMapper::toDto);
    }


    /**
     * Get one pdsCourseTopic by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<PdsCourseTopicDTO> findOne(String id) {
        log.debug("Request to get PdsCourseTopic : {}", id);
        return pdsCourseTopicRepository.findById(id)
            .map(pdsCourseTopicMapper::toDto);
    }

    /**
     * Delete the pdsCourseTopic by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete PdsCourseTopic : {}", id);
        pdsCourseTopicRepository.deleteById(id);
    }
}
