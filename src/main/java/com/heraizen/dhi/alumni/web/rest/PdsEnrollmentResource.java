package com.heraizen.dhi.alumni.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.heraizen.dhi.alumni.service.PdsEnrollmentService;
import com.heraizen.dhi.alumni.service.dto.PdsEnrollmentDTO;
import com.heraizen.dhi.alumni.service.util.DhiHeaderUtil;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing
 * {@link com.heraizen.dhi.alumni.domain.PdsEnrollment}.
 */
@RestController
@RequestMapping("/api")
public class PdsEnrollmentResource {

    private final Logger log = LoggerFactory.getLogger(PdsEnrollmentResource.class);

    private static final String ENTITY_NAME = "pdsEnrollment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PdsEnrollmentService pdsEnrollmentService;

    public PdsEnrollmentResource(PdsEnrollmentService pdsEnrollmentService) {
	this.pdsEnrollmentService = pdsEnrollmentService;
    }

    /**
     * {@code POST  /pds-enrollments} : Create a new pdsEnrollment.
     *
     * @param pdsEnrollmentDTO the pdsEnrollmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new pdsEnrollmentDTO, or with status
     *         {@code 400 (Bad Request)} if the pdsEnrollment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pds-enrollments")
    public ResponseEntity<PdsEnrollmentDTO> createPdsEnrollment(@Valid @RequestBody PdsEnrollmentDTO pdsEnrollmentDTO)
	    throws URISyntaxException {
	log.debug("REST request to save PdsEnrollment : {}", pdsEnrollmentDTO);
	if (pdsEnrollmentDTO.getId() != null) {
	    throw new BadRequestAlertException("A new pdsEnrollment cannot already have an ID", ENTITY_NAME,
		    "idexists");
	}
	PdsEnrollmentDTO result = pdsEnrollmentService.save(pdsEnrollmentDTO);
	return ResponseEntity.created(new URI("/api/pds-enrollments/" + result.getId()))
		.headers(DhiHeaderUtil.createAlert(applicationName, "You have been successfully enrolled to the PDS Service ",
			result.getEnrolledTo().getProfDevServiceName()))
		.body(result);
    }

    /**
     * {@code PUT  /pds-enrollments} : Updates an existing pdsEnrollment.
     *
     * @param pdsEnrollmentDTO the pdsEnrollmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated pdsEnrollmentDTO, or with status
     *         {@code 400 (Bad Request)} if the pdsEnrollmentDTO is not valid, or
     *         with status {@code 500 (Internal Server Error)} if the
     *         pdsEnrollmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pds-enrollments")
    public ResponseEntity<PdsEnrollmentDTO> updatePdsEnrollment(@Valid @RequestBody PdsEnrollmentDTO pdsEnrollmentDTO)
	    throws URISyntaxException {
	log.debug("REST request to update PdsEnrollment : {}", pdsEnrollmentDTO);
	if (pdsEnrollmentDTO.getId() == null) {
	    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
	}
	PdsEnrollmentDTO result = pdsEnrollmentService.save(pdsEnrollmentDTO);
	return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
		pdsEnrollmentDTO.getId().toString())).body(result);
    }

    /**
     * {@code GET  /pds-enrollments} : get all the pdsEnrollments.
     *
     * 
     * @param pageable the pagination information.
     * 
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of pdsEnrollments in body.
     */
    @GetMapping("/pds-enrollments")
    public ResponseEntity<List<PdsEnrollmentDTO>> getAllPdsEnrollments(Pageable pageable) {
	log.debug("REST request to get a page of PdsEnrollments");
	Page<PdsEnrollmentDTO> page = pdsEnrollmentService.findAll(pageable);
	HttpHeaders headers = PaginationUtil
		.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
	return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pds-enrollments/:id} : get the "id" pdsEnrollment.
     *
     * @param id the id of the pdsEnrollmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the pdsEnrollmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pds-enrollments/{id}")
    public ResponseEntity<PdsEnrollmentDTO> getPdsEnrollment(@PathVariable String id) {
	log.debug("REST request to get PdsEnrollment : {}", id);
	Optional<PdsEnrollmentDTO> pdsEnrollmentDTO = pdsEnrollmentService.findOne(id);
	return ResponseUtil.wrapOrNotFound(pdsEnrollmentDTO);
    }

    /**
     * {@code DELETE  /pds-enrollments/:id} : delete the "id" pdsEnrollment.
     *
     * @param id the id of the pdsEnrollmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pds-enrollments/{id}")
    public ResponseEntity<Void> deletePdsEnrollment(@PathVariable String id) {
	log.debug("REST request to delete PdsEnrollment : {}", id);
	pdsEnrollmentService.delete(id);
	return ResponseEntity.noContent()
		.headers(DhiHeaderUtil.createAlert(applicationName, "You have been unenrolled", id)).build();
    }
}
