package com.heraizen.dhi.alumni.service.impl;

import com.heraizen.dhi.alumni.service.PdsScheduleService;
import com.heraizen.dhi.alumni.domain.PdsSchedule;
import com.heraizen.dhi.alumni.repository.PdsScheduleRepository;
import com.heraizen.dhi.alumni.service.dto.PdsScheduleDTO;
import com.heraizen.dhi.alumni.service.mapper.PdsScheduleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PdsSchedule}.
 */
@Service
public class PdsScheduleServiceImpl implements PdsScheduleService {

    private final Logger log = LoggerFactory.getLogger(PdsScheduleServiceImpl.class);

    private final PdsScheduleRepository pdsScheduleRepository;

    private final PdsScheduleMapper pdsScheduleMapper;

    public PdsScheduleServiceImpl(PdsScheduleRepository pdsScheduleRepository, PdsScheduleMapper pdsScheduleMapper) {
        this.pdsScheduleRepository = pdsScheduleRepository;
        this.pdsScheduleMapper = pdsScheduleMapper;
    }

    /**
     * Save a pdsSchedule.
     *
     * @param pdsScheduleDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PdsScheduleDTO save(PdsScheduleDTO pdsScheduleDTO) {
        log.debug("Request to save PdsSchedule : {}", pdsScheduleDTO);
        PdsSchedule pdsSchedule = pdsScheduleMapper.toEntity(pdsScheduleDTO);
        pdsSchedule = pdsScheduleRepository.save(pdsSchedule);
        return pdsScheduleMapper.toDto(pdsSchedule);
    }

    /**
     * Get all the pdsSchedules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<PdsScheduleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PdsSchedules");
        return pdsScheduleRepository.findAll(pageable)
            .map(pdsScheduleMapper::toDto);
    }

    /**
     * Get all the pdsSchedules with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<PdsScheduleDTO> findAllWithEagerRelationships(Pageable pageable) {
        return pdsScheduleRepository.findAllWithEagerRelationships(pageable).map(pdsScheduleMapper::toDto);
    }
    

    /**
     * Get one pdsSchedule by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<PdsScheduleDTO> findOne(String id) {
        log.debug("Request to get PdsSchedule : {}", id);
        return pdsScheduleRepository.findOneWithEagerRelationships(id)
            .map(pdsScheduleMapper::toDto);
    }

    /**
     * Delete the pdsSchedule by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete PdsSchedule : {}", id);
        pdsScheduleRepository.deleteById(id);
    }
}
