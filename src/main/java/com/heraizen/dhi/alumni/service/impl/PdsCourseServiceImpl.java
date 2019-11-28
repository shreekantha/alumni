package com.heraizen.dhi.alumni.service.impl;

import com.heraizen.dhi.alumni.service.PdsCourseService;
import com.heraizen.dhi.alumni.domain.PdsCourse;
import com.heraizen.dhi.alumni.repository.PdsCourseRepository;
import com.heraizen.dhi.alumni.service.dto.PdsCourseDTO;
import com.heraizen.dhi.alumni.service.mapper.PdsCourseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PdsCourse}.
 */
@Service
public class PdsCourseServiceImpl implements PdsCourseService {

    private final Logger log = LoggerFactory.getLogger(PdsCourseServiceImpl.class);

    private final PdsCourseRepository pdsCourseRepository;

    private final PdsCourseMapper pdsCourseMapper;

    public PdsCourseServiceImpl(PdsCourseRepository pdsCourseRepository, PdsCourseMapper pdsCourseMapper) {
        this.pdsCourseRepository = pdsCourseRepository;
        this.pdsCourseMapper = pdsCourseMapper;
    }

    /**
     * Save a pdsCourse.
     *
     * @param pdsCourseDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PdsCourseDTO save(PdsCourseDTO pdsCourseDTO) {
        log.debug("Request to save PdsCourse : {}", pdsCourseDTO);
        PdsCourse pdsCourse = pdsCourseMapper.toEntity(pdsCourseDTO);
        pdsCourse = pdsCourseRepository.save(pdsCourse);
        return pdsCourseMapper.toDto(pdsCourse);
    }

    /**
     * Get all the pdsCourses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<PdsCourseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PdsCourses");
        return pdsCourseRepository.findAll(pageable)
            .map(pdsCourseMapper::toDto);
    }


    /**
     * Get one pdsCourse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<PdsCourseDTO> findOne(String id) {
        log.debug("Request to get PdsCourse : {}", id);
        return pdsCourseRepository.findById(id)
            .map(pdsCourseMapper::toDto);
    }

    /**
     * Delete the pdsCourse by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete PdsCourse : {}", id);
        pdsCourseRepository.deleteById(id);
    }
}
