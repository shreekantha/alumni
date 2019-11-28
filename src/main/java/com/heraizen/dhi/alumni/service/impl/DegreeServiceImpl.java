package com.heraizen.dhi.alumni.service.impl;

import com.heraizen.dhi.alumni.service.DegreeService;
import com.heraizen.dhi.alumni.domain.Degree;
import com.heraizen.dhi.alumni.repository.DegreeRepository;
import com.heraizen.dhi.alumni.service.dto.DegreeDTO;
import com.heraizen.dhi.alumni.service.mapper.DegreeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Degree}.
 */
@Service
public class DegreeServiceImpl implements DegreeService {

    private final Logger log = LoggerFactory.getLogger(DegreeServiceImpl.class);

    private final DegreeRepository degreeRepository;

    private final DegreeMapper degreeMapper;

    public DegreeServiceImpl(DegreeRepository degreeRepository, DegreeMapper degreeMapper) {
        this.degreeRepository = degreeRepository;
        this.degreeMapper = degreeMapper;
    }

    /**
     * Save a degree.
     *
     * @param degreeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DegreeDTO save(DegreeDTO degreeDTO) {
        log.debug("Request to save Degree : {}", degreeDTO);
        Degree degree = degreeMapper.toEntity(degreeDTO);
        degree = degreeRepository.save(degree);
        return degreeMapper.toDto(degree);
    }

    /**
     * Get all the degrees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<DegreeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Degrees");
        return degreeRepository.findAll(pageable)
            .map(degreeMapper::toDto);
    }


    /**
     * Get one degree by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<DegreeDTO> findOne(String id) {
        log.debug("Request to get Degree : {}", id);
        return degreeRepository.findById(id)
            .map(degreeMapper::toDto);
    }

    /**
     * Delete the degree by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Degree : {}", id);
        degreeRepository.deleteById(id);
    }
}
