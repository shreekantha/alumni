package com.heraizen.dhi.alumni.service.impl;

import com.heraizen.dhi.alumni.service.MeetReqTopicService;
import com.heraizen.dhi.alumni.domain.MeetReqTopic;
import com.heraizen.dhi.alumni.repository.MeetReqTopicRepository;
import com.heraizen.dhi.alumni.service.dto.MeetReqTopicDTO;
import com.heraizen.dhi.alumni.service.mapper.MeetReqTopicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MeetReqTopic}.
 */
@Service
public class MeetReqTopicServiceImpl implements MeetReqTopicService {

    private final Logger log = LoggerFactory.getLogger(MeetReqTopicServiceImpl.class);

    private final MeetReqTopicRepository meetReqTopicRepository;

    private final MeetReqTopicMapper meetReqTopicMapper;

    public MeetReqTopicServiceImpl(MeetReqTopicRepository meetReqTopicRepository, MeetReqTopicMapper meetReqTopicMapper) {
        this.meetReqTopicRepository = meetReqTopicRepository;
        this.meetReqTopicMapper = meetReqTopicMapper;
    }

    /**
     * Save a meetReqTopic.
     *
     * @param meetReqTopicDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MeetReqTopicDTO save(MeetReqTopicDTO meetReqTopicDTO) {
        log.debug("Request to save MeetReqTopic : {}", meetReqTopicDTO);
        MeetReqTopic meetReqTopic = meetReqTopicMapper.toEntity(meetReqTopicDTO);
        meetReqTopic = meetReqTopicRepository.save(meetReqTopic);
        return meetReqTopicMapper.toDto(meetReqTopic);
    }

    /**
     * Get all the meetReqTopics.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<MeetReqTopicDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MeetReqTopics");
        return meetReqTopicRepository.findAll(pageable)
            .map(meetReqTopicMapper::toDto);
    }


    /**
     * Get one meetReqTopic by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<MeetReqTopicDTO> findOne(String id) {
        log.debug("Request to get MeetReqTopic : {}", id);
        return meetReqTopicRepository.findById(id)
            .map(meetReqTopicMapper::toDto);
    }

    /**
     * Delete the meetReqTopic by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete MeetReqTopic : {}", id);
        meetReqTopicRepository.deleteById(id);
    }
}
