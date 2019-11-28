package com.heraizen.dhi.alumni.service.impl;

import com.heraizen.dhi.alumni.service.PdsEnrollmentService;
import com.heraizen.dhi.alumni.domain.PdsEnrollment;
import com.heraizen.dhi.alumni.domain.PdsSchedule;
import com.heraizen.dhi.alumni.repository.PdsEnrollmentRepository;
import com.heraizen.dhi.alumni.repository.PdsScheduleRepository;
import com.heraizen.dhi.alumni.security.SecurityUtils;
import com.heraizen.dhi.alumni.service.dto.PdsEnrollmentDTO;
import com.heraizen.dhi.alumni.service.mapper.PdsEnrollmentMapper;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Service Implementation for managing {@link PdsEnrollment}.
 */
@Service
public class PdsEnrollmentServiceImpl implements PdsEnrollmentService {

    private final Logger log = LoggerFactory.getLogger(PdsEnrollmentServiceImpl.class);

    private final PdsEnrollmentRepository pdsEnrollmentRepository;

    private final PdsEnrollmentMapper pdsEnrollmentMapper;

    private final PdsScheduleRepository pdsScheduleRepository;

    public PdsEnrollmentServiceImpl(PdsEnrollmentRepository pdsEnrollmentRepository,
	    PdsEnrollmentMapper pdsEnrollmentMapper, PdsScheduleRepository pdsScheduleRepository) {
	this.pdsEnrollmentRepository = pdsEnrollmentRepository;
	this.pdsEnrollmentMapper = pdsEnrollmentMapper;
	this.pdsScheduleRepository = pdsScheduleRepository;
    }

    /**
     * Save a pdsEnrollment.
     *
     * @param pdsEnrollmentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PdsEnrollmentDTO save(PdsEnrollmentDTO pdsEnrollmentDTO) {
	log.debug("Request to save PdsEnrollment : {}", pdsEnrollmentDTO);
	if (!pdsEnrollmentRepository
		.findByEnrolledToIdAndEnrolledBy(pdsEnrollmentDTO.getEnrolledToId(), pdsEnrollmentDTO.getEnrolledById())
		.isPresent()) {
	    log.debug("Enrolledto id {}", pdsEnrollmentDTO.getEnrolledToId());
	    PdsEnrollment pdsEnrollment = pdsEnrollmentMapper.toEntity(pdsEnrollmentDTO);
	    PdsSchedule pdsSchedule = pdsScheduleRepository.findOneWithEagerRelationships(pdsEnrollmentDTO.getEnrolledToId()).get();
	 
	    log.debug("pds schedule {}",pdsSchedule);
	    pdsEnrollment.setPdsName(pdsSchedule.getProfDevService().getName());
	    pdsEnrollment.setFaculty(new ArrayList<>(pdsSchedule.getUsers()).get(0).getFirstName());
	    pdsEnrollment = pdsEnrollmentRepository.save(pdsEnrollment);
	    return pdsEnrollmentMapper.toDto(pdsEnrollment);
	} else
	    throw new BadRequestAlertException("You have been already registered to this service ", "pdsEnrollment",
		    "idexists");

    }

    /**
     * Get all the pdsEnrollments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<PdsEnrollmentDTO> findAll(Pageable pageable) {
	log.debug("Request to get all PdsEnrollments");
	String userLogin = SecurityUtils.getCurrentUserLogin()
		.orElseThrow(() -> new IllegalArgumentException("Current user login not found"));
	return pdsEnrollmentRepository.findAllWithEagerRelationships(userLogin, pageable)
		.map(pdsEnrollmentMapper::toDto);
    }

    /**
     * Get one pdsEnrollment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<PdsEnrollmentDTO> findOne(String id) {
	log.debug("Request to get PdsEnrollment : {}", id);
	return pdsEnrollmentRepository.findById(id).map(pdsEnrollmentMapper::toDto);
    }

    /**
     * Delete the pdsEnrollment by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
	log.debug("Request to delete PdsEnrollment : {}", id);
	pdsEnrollmentRepository.deleteById(id);
    }
}
