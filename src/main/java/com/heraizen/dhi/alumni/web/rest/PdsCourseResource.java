package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.service.PdsCourseService;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;
import com.heraizen.dhi.alumni.service.dto.PdsCourseDTO;

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
 * REST controller for managing {@link com.heraizen.dhi.alumni.domain.PdsCourse}.
 */
@RestController
@RequestMapping("/api")
public class PdsCourseResource {

    private final Logger log = LoggerFactory.getLogger(PdsCourseResource.class);

    private static final String ENTITY_NAME = "PDS Course";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PdsCourseService pdsCourseService;

    public PdsCourseResource(PdsCourseService pdsCourseService) {
        this.pdsCourseService = pdsCourseService;
    }

    /**
     * {@code POST  /pds-courses} : Create a new pdsCourse.
     *
     * @param pdsCourseDTO the pdsCourseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pdsCourseDTO, or with status {@code 400 (Bad Request)} if the pdsCourse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pds-courses")
    public ResponseEntity<PdsCourseDTO> createPdsCourse(@Valid @RequestBody PdsCourseDTO pdsCourseDTO) throws URISyntaxException {
        log.debug("REST request to save PdsCourse : {}", pdsCourseDTO);
        if (pdsCourseDTO.getId() != null) {
            throw new BadRequestAlertException("A new pdsCourse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PdsCourseDTO result = pdsCourseService.save(pdsCourseDTO);
        return ResponseEntity.created(new URI("/api/pds-courses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getCourseName().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pds-courses} : Updates an existing pdsCourse.
     *
     * @param pdsCourseDTO the pdsCourseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pdsCourseDTO,
     * or with status {@code 400 (Bad Request)} if the pdsCourseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pdsCourseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pds-courses")
    public ResponseEntity<PdsCourseDTO> updatePdsCourse(@Valid @RequestBody PdsCourseDTO pdsCourseDTO) throws URISyntaxException {
        log.debug("REST request to update PdsCourse : {}", pdsCourseDTO);
        if (pdsCourseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PdsCourseDTO result = pdsCourseService.save(pdsCourseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pdsCourseDTO.getCourseName().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pds-courses} : get all the pdsCourses.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pdsCourses in body.
     */
    @GetMapping("/pds-courses")
    public ResponseEntity<List<PdsCourseDTO>> getAllPdsCourses(Pageable pageable) {
        log.debug("REST request to get a page of PdsCourses");
        Page<PdsCourseDTO> page = pdsCourseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pds-courses/:id} : get the "id" pdsCourse.
     *
     * @param id the id of the pdsCourseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pdsCourseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pds-courses/{id}")
    public ResponseEntity<PdsCourseDTO> getPdsCourse(@PathVariable String id) {
        log.debug("REST request to get PdsCourse : {}", id);
        Optional<PdsCourseDTO> pdsCourseDTO = pdsCourseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pdsCourseDTO);
    }

    /**
     * {@code DELETE  /pds-courses/:id} : delete the "id" pdsCourse.
     *
     * @param id the id of the pdsCourseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pds-courses/{id}")
    public ResponseEntity<Void> deletePdsCourse(@PathVariable String id) {
        log.debug("REST request to delete PdsCourse : {}", id);
        pdsCourseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
