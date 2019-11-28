package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.service.PdsScheduleService;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;
import com.heraizen.dhi.alumni.service.dto.PdsScheduleDTO;
import com.heraizen.dhi.alumni.service.util.DhiHeaderUtil;

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
 * REST controller for managing {@link com.heraizen.dhi.alumni.domain.PdsSchedule}.
 */
@RestController
@RequestMapping("/api")
public class PdsScheduleResource {

    
    private final Logger log = LoggerFactory.getLogger(PdsScheduleResource.class);

    private static final String ENTITY_NAME = "PDS Schedule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PdsScheduleService pdsScheduleService;

    public PdsScheduleResource(PdsScheduleService pdsScheduleService) {
        this.pdsScheduleService = pdsScheduleService;
    }

    /**
     * {@code POST  /pds-schedules} : Create a new pdsSchedule.
     *
     * @param pdsScheduleDTO the pdsScheduleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pdsScheduleDTO, or with status {@code 400 (Bad Request)} if the pdsSchedule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pds-schedules")
    public ResponseEntity<PdsScheduleDTO> createPdsSchedule(@Valid @RequestBody PdsScheduleDTO pdsScheduleDTO) throws URISyntaxException {
        log.debug("REST request to save PdsSchedule : {}", pdsScheduleDTO);
        if (pdsScheduleDTO.getId() != null) {
            throw new BadRequestAlertException("A new pdsSchedule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PdsScheduleDTO result = pdsScheduleService.save(pdsScheduleDTO);
        return ResponseEntity.created(new URI("/api/pds-schedules/" + result.getId()))
            .headers(DhiHeaderUtil.createAlert(applicationName, "PDS Schedule is created successfully", result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pds-schedules} : Updates an existing pdsSchedule.
     *
     * @param pdsScheduleDTO the pdsScheduleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pdsScheduleDTO,
     * or with status {@code 400 (Bad Request)} if the pdsScheduleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pdsScheduleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pds-schedules")
    public ResponseEntity<PdsScheduleDTO> updatePdsSchedule(@Valid @RequestBody PdsScheduleDTO pdsScheduleDTO) throws URISyntaxException {
        log.debug("REST request to update PdsSchedule : {}", pdsScheduleDTO);
        if (pdsScheduleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PdsScheduleDTO result = pdsScheduleService.save(pdsScheduleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pdsScheduleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pds-schedules} : get all the pdsSchedules.
     *

     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pdsSchedules in body.
     */
    @GetMapping("/pds-schedules")
    public ResponseEntity<List<PdsScheduleDTO>> getAllPdsSchedules(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of PdsSchedules");
        Page<PdsScheduleDTO> page;
        if (eagerload) {
            page = pdsScheduleService.findAllWithEagerRelationships(pageable);
        } else {
            page = pdsScheduleService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pds-schedules/:id} : get the "id" pdsSchedule.
     *
     * @param id the id of the pdsScheduleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pdsScheduleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pds-schedules/{id}")
    public ResponseEntity<PdsScheduleDTO> getPdsSchedule(@PathVariable String id) {
        log.debug("REST request to get PdsSchedule : {}", id);
        Optional<PdsScheduleDTO> pdsScheduleDTO = pdsScheduleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pdsScheduleDTO);
    }

    /**
     * {@code DELETE  /pds-schedules/:id} : delete the "id" pdsSchedule.
     *
     * @param id the id of the pdsScheduleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pds-schedules/{id}")
    public ResponseEntity<Void> deletePdsSchedule(@PathVariable String id) {
        log.debug("REST request to delete PdsSchedule : {}", id);
        pdsScheduleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
