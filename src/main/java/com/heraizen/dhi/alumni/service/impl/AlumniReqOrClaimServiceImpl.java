package com.heraizen.dhi.alumni.service.impl;

import com.heraizen.dhi.alumni.service.AlumniReqOrClaimService;
import com.heraizen.dhi.alumni.domain.AlumniReqOrClaim;
import com.heraizen.dhi.alumni.repository.AlumniReqOrClaimRepository;
import com.heraizen.dhi.alumni.service.dto.AlumniReqOrClaimDTO;
import com.heraizen.dhi.alumni.service.mapper.AlumniReqOrClaimMapper;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

/**
 * Service Implementation for managing {@link AlumniReqOrClaim}.
 */
@Service
public class AlumniReqOrClaimServiceImpl implements AlumniReqOrClaimService {

    private final Logger log = LoggerFactory.getLogger(AlumniReqOrClaimServiceImpl.class);

    private final AlumniReqOrClaimRepository alumniReqOrClaimRepository;

    private final AlumniReqOrClaimMapper alumniReqOrClaimMapper;

    public AlumniReqOrClaimServiceImpl(AlumniReqOrClaimRepository alumniReqOrClaimRepository,
	    AlumniReqOrClaimMapper alumniReqOrClaimMapper) {
	this.alumniReqOrClaimRepository = alumniReqOrClaimRepository;
	this.alumniReqOrClaimMapper = alumniReqOrClaimMapper;
    }

    /**
     * Save a alumniReqOrClaim.
     *
     * @param alumniReqOrClaimDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AlumniReqOrClaimDTO save(AlumniReqOrClaimDTO alumniReqOrClaimDTO) {
	log.debug("Request to save AlumniReqOrClaim : {}", alumniReqOrClaimDTO);
	AlumniReqOrClaim alumniReqOrClaim = alumniReqOrClaimMapper.toEntity(alumniReqOrClaimDTO);
	alumniReqOrClaim = alumniReqOrClaimRepository.save(alumniReqOrClaim);
	return alumniReqOrClaimMapper.toDto(alumniReqOrClaim);
    }

    /**
     * Get all the alumniReqOrClaims.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AlumniReqOrClaimDTO> findAlumniReqOrClaims(String assignee, String requestedBy, Pageable pageable) {
	log.debug("Request to get all AlumniReqOrClaims");
	if (assignee != null)
	    return alumniReqOrClaimRepository.findByAssigneeId(assignee, pageable).map(alumniReqOrClaimMapper::toDto);
	if (requestedBy != null)
	    return alumniReqOrClaimRepository.findByRequestById(requestedBy, pageable).map(alumniReqOrClaimMapper::toDto);

	throw new BadRequestAlertException("No alumni requests or claims found", "alumniReqOrClaim", "");
    }

    /**
     * Get all the alumniReqOrClaims.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AlumniReqOrClaimDTO> findAll(Pageable pageable) {
	log.debug("Request to get all AlumniReqOrClaims");
	return alumniReqOrClaimRepository.findAll(pageable).map(alumniReqOrClaimMapper::toDto);
    }

    /**
     * Get one alumniReqOrClaim by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<AlumniReqOrClaimDTO> findOne(String id) {
	log.debug("Request to get AlumniReqOrClaim : {}", id);
	return alumniReqOrClaimRepository.findById(id).map(alumniReqOrClaimMapper::toDto);
    }

    /**
     * Delete the alumniReqOrClaim by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
	log.debug("Request to delete AlumniReqOrClaim : {}", id);
	alumniReqOrClaimRepository.deleteById(id);
    }
}
