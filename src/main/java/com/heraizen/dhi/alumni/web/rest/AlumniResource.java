package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.service.AlumniService;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;
import com.heraizen.dhi.alumni.service.dto.AlumniDTO;

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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.heraizen.dhi.alumni.domain.Alumni}.
 */
@RestController
@RequestMapping("/api")
public class AlumniResource {

    private final Logger log = LoggerFactory.getLogger(AlumniResource.class);

    private static final String ENTITY_NAME = "alumni";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlumniService alumniService;

    public AlumniResource(AlumniService alumniService) {
	this.alumniService = alumniService;
    }

    /**
     * {@code POST  /alumni} : Create a new alumni.
     *
     * @param alumniDTO the alumniDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new alumniDTO, or with status {@code 400 (Bad Request)} if
     *         the alumni has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/alumni")
    public ResponseEntity<AlumniDTO> createAlumni(@RequestBody AlumniDTO alumniDTO) throws URISyntaxException {
	log.debug("REST request to save Alumni : {}", alumniDTO);
	if (alumniDTO.getId() != null) {
	    throw new BadRequestAlertException("A new alumni cannot already have an ID", ENTITY_NAME, "idexists");
	}
	AlumniDTO result = alumniService.save(alumniDTO);
	return ResponseEntity
		.created(new URI("/api/alumni/" + result.getId())).headers(HeaderUtil
			.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
		.body(result);
    }

    /**
     * {@code PUT  /alumni} : Updates an existing alumni.
     *
     * @param alumniDTO the alumniDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated alumniDTO, or with status {@code 400 (Bad Request)} if
     *         the alumniDTO is not valid, or with status
     *         {@code 500 (Internal Server Error)} if the alumniDTO couldn't be
     *         updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/alumni")
    public ResponseEntity<AlumniDTO> updateAlumni(@RequestBody AlumniDTO alumniDTO) throws URISyntaxException {
	log.debug("REST request to update Alumni : {}", alumniDTO);
	if (alumniDTO.getId() == null) {
	    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
	}
	AlumniDTO result = alumniService.save(alumniDTO);
	return ResponseEntity.ok().headers(
		HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, alumniDTO.getId().toString()))
		.body(result);
    }

    /**
     * {@code GET  /alumni} : get all the alumni.
     *
     * 
     * @param pageable the pagination information.
     * 
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of alumni in body.
     */
    @GetMapping("/alumni")
    public ResponseEntity<List<AlumniDTO>> getAllAlumni(@RequestParam(value = "position") String position,
	    @RequestParam(value = "experience") Double experience,
	    @RequestParam(value = "year-of-graduation") String yearOfGraduation,
	    @RequestParam(value = "department") String department, Pageable pageable) {
	log.debug("REST request to get a page of Alumni");
	Page<AlumniDTO> page = alumniService.findAll(position, experience, yearOfGraduation, department, pageable);
	HttpHeaders headers = PaginationUtil
		.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
	return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /alumni/:id} : get the "id" alumni.
     *
     * @param id the id of the alumniDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the alumniDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/alumni/{id}")
    public ResponseEntity<AlumniDTO> getAlumni(@PathVariable String id) {
	log.debug("REST request to get Alumni : {}", id);
	Optional<AlumniDTO> alumniDTO = alumniService.findOne(id);
	return ResponseUtil.wrapOrNotFound(alumniDTO);
    }

    /**
     * {@code DELETE  /alumni/:id} : delete the "id" alumni.
     *
     * @param id the id of the alumniDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/alumni/{id}")
    public ResponseEntity<Void> deleteAlumni(@PathVariable String id) {
	log.debug("REST request to delete Alumni : {}", id);
	alumniService.delete(id);
	return ResponseEntity.noContent()
		.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
