package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.service.AspiredRoleService;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;
import com.heraizen.dhi.alumni.service.dto.AspiredRoleDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.heraizen.dhi.alumni.domain.AspiredRole}.
 */
@RestController
@RequestMapping("/api")
public class AspiredRoleResource {

    private final Logger log = LoggerFactory.getLogger(AspiredRoleResource.class);

    private static final String ENTITY_NAME = "Aspired Role";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AspiredRoleService aspiredRoleService;

    public AspiredRoleResource(AspiredRoleService aspiredRoleService) {
        this.aspiredRoleService = aspiredRoleService;
    }

    /**
     * {@code POST  /aspired-roles} : Create a new aspiredRole.
     *
     * @param aspiredRoleDTO the aspiredRoleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new aspiredRoleDTO, or with status {@code 400 (Bad Request)} if the aspiredRole has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/aspired-roles")
    public ResponseEntity<AspiredRoleDTO> createAspiredRole(@Valid @RequestBody AspiredRoleDTO aspiredRoleDTO) throws URISyntaxException {
        log.debug("REST request to save AspiredRole : {}", aspiredRoleDTO);
        if (aspiredRoleDTO.getId() != null) {
            throw new BadRequestAlertException("A new aspiredRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AspiredRoleDTO result = aspiredRoleService.save(aspiredRoleDTO);
        return ResponseEntity.created(new URI("/api/aspired-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getName().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /aspired-roles} : Updates an existing aspiredRole.
     *
     * @param aspiredRoleDTO the aspiredRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aspiredRoleDTO,
     * or with status {@code 400 (Bad Request)} if the aspiredRoleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the aspiredRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/aspired-roles")
    public ResponseEntity<AspiredRoleDTO> updateAspiredRole(@Valid @RequestBody AspiredRoleDTO aspiredRoleDTO) throws URISyntaxException {
        log.debug("REST request to update AspiredRole : {}", aspiredRoleDTO);
        if (aspiredRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AspiredRoleDTO result = aspiredRoleService.save(aspiredRoleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, aspiredRoleDTO.getName().toString()))
            .body(result);
    }

    /**
     * {@code GET  /aspired-roles} : get all the aspiredRoles.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of aspiredRoles in body.
     */
    @GetMapping("/aspired-roles")
    public ResponseEntity<List<AspiredRoleDTO>> getAllAspiredRoles(Pageable pageable) {
        log.debug("REST request to get a page of AspiredRoles");
        Page<AspiredRoleDTO> page = aspiredRoleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /aspired-roles/:id} : get the "id" aspiredRole.
     *
     * @param id the id of the aspiredRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the aspiredRoleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/aspired-roles/{id}")
    public ResponseEntity<AspiredRoleDTO> getAspiredRole(@PathVariable String id) {
        log.debug("REST request to get AspiredRole : {}", id);
        Optional<AspiredRoleDTO> aspiredRoleDTO = aspiredRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(aspiredRoleDTO);
    }

    /**
     * {@code DELETE  /aspired-roles/:id} : delete the "id" aspiredRole.
     *
     * @param id the id of the aspiredRoleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/aspired-roles/{id}")
    public ResponseEntity<Void> deleteAspiredRole(@PathVariable String id) {
        log.debug("REST request to delete AspiredRole : {}", id);
        aspiredRoleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
