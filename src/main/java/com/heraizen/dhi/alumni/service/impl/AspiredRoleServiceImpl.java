package com.heraizen.dhi.alumni.service.impl;

import com.heraizen.dhi.alumni.service.AspiredRoleService;
import com.heraizen.dhi.alumni.domain.AspiredRole;
import com.heraizen.dhi.alumni.repository.AspiredRoleRepository;
import com.heraizen.dhi.alumni.service.dto.AspiredRoleDTO;
import com.heraizen.dhi.alumni.service.mapper.AspiredRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AspiredRole}.
 */
@Service
public class AspiredRoleServiceImpl implements AspiredRoleService {

    private final Logger log = LoggerFactory.getLogger(AspiredRoleServiceImpl.class);

    private final AspiredRoleRepository aspiredRoleRepository;

    private final AspiredRoleMapper aspiredRoleMapper;

    public AspiredRoleServiceImpl(AspiredRoleRepository aspiredRoleRepository, AspiredRoleMapper aspiredRoleMapper) {
        this.aspiredRoleRepository = aspiredRoleRepository;
        this.aspiredRoleMapper = aspiredRoleMapper;
    }

    /**
     * Save a aspiredRole.
     *
     * @param aspiredRoleDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AspiredRoleDTO save(AspiredRoleDTO aspiredRoleDTO) {
        log.debug("Request to save AspiredRole : {}", aspiredRoleDTO);
        AspiredRole aspiredRole = aspiredRoleMapper.toEntity(aspiredRoleDTO);
        aspiredRole = aspiredRoleRepository.save(aspiredRole);
        return aspiredRoleMapper.toDto(aspiredRole);
    }

    /**
     * Get all the aspiredRoles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AspiredRoleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AspiredRoles");
        return aspiredRoleRepository.findAll(pageable)
            .map(aspiredRoleMapper::toDto);
    }


    /**
     * Get one aspiredRole by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<AspiredRoleDTO> findOne(String id) {
        log.debug("Request to get AspiredRole : {}", id);
        return aspiredRoleRepository.findById(id)
            .map(aspiredRoleMapper::toDto);
    }

    /**
     * Delete the aspiredRole by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete AspiredRole : {}", id);
        aspiredRoleRepository.deleteById(id);
    }
}
