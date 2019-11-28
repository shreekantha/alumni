package com.heraizen.dhi.alumni.service.impl;

import com.heraizen.dhi.alumni.service.ProfDevServiceService;
import com.heraizen.dhi.alumni.domain.ProfDevService;
import com.heraizen.dhi.alumni.repository.ProfDevServiceRepository;
import com.heraizen.dhi.alumni.service.dto.ProfDevServiceDTO;
import com.heraizen.dhi.alumni.service.mapper.ProfDevServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProfDevService}.
 */
@Service
public class ProfDevServiceServiceImpl implements ProfDevServiceService {

    private final Logger log = LoggerFactory.getLogger(ProfDevServiceServiceImpl.class);

    private final ProfDevServiceRepository profDevServiceRepository;

    private final ProfDevServiceMapper profDevServiceMapper;

    public ProfDevServiceServiceImpl(ProfDevServiceRepository profDevServiceRepository, ProfDevServiceMapper profDevServiceMapper) {
        this.profDevServiceRepository = profDevServiceRepository;
        this.profDevServiceMapper = profDevServiceMapper;
    }

    /**
     * Save a profDevService.
     *
     * @param profDevServiceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProfDevServiceDTO save(ProfDevServiceDTO profDevServiceDTO) {
        log.debug("Request to save ProfDevService : {}", profDevServiceDTO);
        ProfDevService profDevService = profDevServiceMapper.toEntity(profDevServiceDTO);
        profDevService = profDevServiceRepository.save(profDevService);
        return profDevServiceMapper.toDto(profDevService);
    }

    /**
     * Get all the profDevServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<ProfDevServiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProfDevServices");
        return profDevServiceRepository.findAll(pageable)
            .map(profDevServiceMapper::toDto);
    }


    /**
     * Get one profDevService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<ProfDevServiceDTO> findOne(String id) {
        log.debug("Request to get ProfDevService : {}", id);
        return profDevServiceRepository.findById(id)
            .map(profDevServiceMapper::toDto);
    }

    /**
     * Delete the profDevService by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete ProfDevService : {}", id);
        profDevServiceRepository.deleteById(id);
    }
}
