package com.heraizen.dhi.alumni.service;

import com.heraizen.dhi.alumni.service.dto.AspiredRoleDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.heraizen.dhi.alumni.domain.AspiredRole}.
 */
public interface AspiredRoleService {

    /**
     * Save a aspiredRole.
     *
     * @param aspiredRoleDTO the entity to save.
     * @return the persisted entity.
     */
    AspiredRoleDTO save(AspiredRoleDTO aspiredRoleDTO);

    /**
     * Get all the aspiredRoles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AspiredRoleDTO> findAll(Pageable pageable);


    /**
     * Get the "id" aspiredRole.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AspiredRoleDTO> findOne(String id);

    /**
     * Delete the "id" aspiredRole.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
