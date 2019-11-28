package com.heraizen.dhi.alumni.service.impl;

import com.heraizen.dhi.alumni.service.AlumniService;
import com.heraizen.dhi.alumni.service.UserService;
import com.heraizen.dhi.alumni.domain.Alumni;
import com.heraizen.dhi.alumni.repository.AlumniRepository;
import com.heraizen.dhi.alumni.service.dto.AlumniDTO;
import com.heraizen.dhi.alumni.service.mapper.AlumniMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Alumni}.
 */
@Service
public class AlumniServiceImpl implements AlumniService {

    private final Logger log = LoggerFactory.getLogger(AlumniServiceImpl.class);

    private final AlumniRepository alumniRepository;

    private final AlumniMapper alumniMapper;
    @Autowired
    private UserService userService;

    public AlumniServiceImpl(AlumniRepository alumniRepository, AlumniMapper alumniMapper) {
	this.alumniRepository = alumniRepository;
	this.alumniMapper = alumniMapper;
    }

    /**
     * Save a alumni.
     *
     * @param alumniDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AlumniDTO save(AlumniDTO alumniDTO) {
	log.debug("Request to save Alumni : {}", alumniDTO);
	Alumni alumni = alumniMapper.toEntity(alumniDTO);
	if (alumniDTO.getId() == null)
	    userService.createUser(alumniDTO);
	alumni = alumniRepository.save(alumni);

	return alumniMapper.toDto(alumni);
    }

    /**
     * Get all the alumni.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AlumniDTO> findAll(Pageable pageable) {
	log.debug("Request to get all Alumni");
	return alumniRepository.findAll(pageable).map(alumniMapper::toDto);
    }

    /**
     * Get one alumni by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<AlumniDTO> findOne(String id) {
	log.debug("Request to get Alumni : {}", id);
	return alumniRepository.findById(id).map(alumniMapper::toDto);
    }

    /**
     * Delete the alumni by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
	log.debug("Request to delete Alumni : {}", id);
	alumniRepository.deleteById(id);
    }

    @Override
    public Page<AlumniDTO> findAll(String position, Double experience, String yearOfGraduation, String department,
	    Pageable pageable) {
	log.debug("Request to get all Alumni {}:{}:{}:{}", position, experience, yearOfGraduation, department);
	if (position == null || experience == null || yearOfGraduation == null || department == null) {
	    return alumniRepository.findAll(pageable).map(alumniMapper::toDto);
	} else
	    return alumniRepository.findAllByExperienceAndJobPositionAndYearOfGraduationAndSpecialization(experience,
		    position, yearOfGraduation, department, pageable).map(alumniMapper::toDto);
    }
}
