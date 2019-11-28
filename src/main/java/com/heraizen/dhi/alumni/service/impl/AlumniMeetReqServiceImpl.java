package com.heraizen.dhi.alumni.service.impl;

import com.heraizen.dhi.alumni.service.AlumniMeetReqService;
import com.heraizen.dhi.alumni.domain.AlumniMeetReq;
import com.heraizen.dhi.alumni.repository.AlumniMeetReqRepository;
import com.heraizen.dhi.alumni.service.dto.AlumniMeetReqDTO;
import com.heraizen.dhi.alumni.service.mapper.AlumniMeetReqMapper;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AlumniMeetReq}.
 */
@Service
public class AlumniMeetReqServiceImpl implements AlumniMeetReqService {

    private final Logger log = LoggerFactory.getLogger(AlumniMeetReqServiceImpl.class);

    private final AlumniMeetReqRepository alumniMeetReqRepository;

    private final AlumniMeetReqMapper alumniMeetReqMapper;

    public AlumniMeetReqServiceImpl(AlumniMeetReqRepository alumniMeetReqRepository,
	    AlumniMeetReqMapper alumniMeetReqMapper) {
	this.alumniMeetReqRepository = alumniMeetReqRepository;
	this.alumniMeetReqMapper = alumniMeetReqMapper;
    }

    /**
     * Save a alumniMeetReq.
     *
     * @param alumniMeetReqDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AlumniMeetReqDTO save(AlumniMeetReqDTO alumniMeetReqDTO) {
	log.debug("Request to save AlumniMeetReq : {}", alumniMeetReqDTO);
	AlumniMeetReq alumniMeetReq = alumniMeetReqMapper.toEntity(alumniMeetReqDTO);
	alumniMeetReq = alumniMeetReqRepository.save(alumniMeetReq);
	return alumniMeetReqMapper.toDto(alumniMeetReq);
    }

    /**
     * Get all the alumniMeetReqs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AlumniMeetReqDTO> findAll(Pageable pageable) {
	log.debug("Request to get all AlumniMeetReqs");
	return alumniMeetReqRepository.findAll(pageable).map(alumniMeetReqMapper::toDto);
    }

    /**
     * Get all the alumniMeetReqs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AlumniMeetReqDTO> findAlumniMeetRequests(String requestedBy, String requestedTo, Pageable pageable) {
	log.debug("Request to get all AlumniMeetReqs");
	if (requestedBy != null)
	    return alumniMeetReqRepository.findByRequestBy(requestedBy, pageable).map(alumniMeetReqMapper::toDto);
	if (requestedTo != null)
	    return alumniMeetReqRepository.findByRequestTo(requestedTo, pageable).map(alumniMeetReqMapper::toDto);

	throw new BadRequestAlertException("No alumni meet requests found", "alumniMeetRequest", "");
    }

    /**
     * Get one alumniMeetReq by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<AlumniMeetReqDTO> findOne(String id) {
	log.debug("Request to get AlumniMeetReq : {}", id);
	return alumniMeetReqRepository.findById(id).map(alumniMeetReqMapper::toDto);
    }

    /**
     * Delete the alumniMeetReq by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
	log.debug("Request to delete AlumniMeetReq : {}", id);
	alumniMeetReqRepository.deleteById(id);
    }
}
