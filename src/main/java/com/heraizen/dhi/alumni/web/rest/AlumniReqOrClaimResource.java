package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.service.AlumniReqOrClaimService;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;
import com.heraizen.dhi.alumni.service.dto.AlumniReqOrClaimDTO;

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
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing
 * {@link com.heraizen.dhi.alumni.domain.AlumniReqOrClaim}.
 */
@RestController
@RequestMapping("/api")
public class AlumniReqOrClaimResource {

    private final Logger log = LoggerFactory.getLogger(AlumniReqOrClaimResource.class);

    private static final String ENTITY_NAME = "Alumni Req/Claim";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlumniReqOrClaimService alumniReqOrClaimService;

    public AlumniReqOrClaimResource(AlumniReqOrClaimService alumniReqOrClaimService) {
	this.alumniReqOrClaimService = alumniReqOrClaimService;
    }

    /**
     * {@code POST  /alumni-req-or-claims} : Create a new alumniReqOrClaim.
     *
     * @param alumniReqOrClaimDTO the alumniReqOrClaimDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new alumniReqOrClaimDTO, or with status
     *         {@code 400 (Bad Request)} if the alumniReqOrClaim has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/alumni-req-or-claims")
    public ResponseEntity<AlumniReqOrClaimDTO> createAlumniReqOrClaim(
	    @Valid @RequestBody AlumniReqOrClaimDTO alumniReqOrClaimDTO) throws URISyntaxException {
	log.debug("REST request to save AlumniReqOrClaim : {}", alumniReqOrClaimDTO);
	if (alumniReqOrClaimDTO.getId() != null) {
	    throw new BadRequestAlertException("A new alumniReqOrClaim cannot already have an ID", ENTITY_NAME,
		    "idexists");
	}
	alumniReqOrClaimDTO.setRequestedDate(Instant.now());
	AlumniReqOrClaimDTO result = alumniReqOrClaimService.save(alumniReqOrClaimDTO);
	return ResponseEntity
		.created(new URI("/api/alumni-req-or-claims/" + result.getId())).headers(HeaderUtil
			.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ""))
		.body(result);
    }

    /**
     * {@code PUT  /alumni-req-or-claims} : Updates an existing alumniReqOrClaim.
     *
     * @param alumniReqOrClaimDTO the alumniReqOrClaimDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated alumniReqOrClaimDTO, or with status
     *         {@code 400 (Bad Request)} if the alumniReqOrClaimDTO is not valid, or
     *         with status {@code 500 (Internal Server Error)} if the
     *         alumniReqOrClaimDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/alumni-req-or-claims")
    public ResponseEntity<AlumniReqOrClaimDTO> updateAlumniReqOrClaim(
	    @Valid @RequestBody AlumniReqOrClaimDTO alumniReqOrClaimDTO) throws URISyntaxException {
	log.debug("REST request to update AlumniReqOrClaim : {}", alumniReqOrClaimDTO);
	if (alumniReqOrClaimDTO.getId() == null) {
	    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
	}
	AlumniReqOrClaimDTO result = alumniReqOrClaimService.save(alumniReqOrClaimDTO);
	return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
		"")).body(result);
    }

    /**
     * {@code GET  /alumni-req-or-claims} : get all the alumniReqOrClaims.
     *
     * 
     * @param pageable the pagination information.
     * 
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of alumniReqOrClaims in body.
     */
    @GetMapping("/alumni-req-or-claims")
    public ResponseEntity<List<AlumniReqOrClaimDTO>> getAllAlumniReqOrClaims(@RequestParam(value="assignee",required=false) String assignee,
	    @RequestParam(value="requested-by",required=false) String requestedBy, Pageable pageable) {
	log.debug("REST request to get a page of AlumniReqOrClaims {}",requestedBy);
	Page<AlumniReqOrClaimDTO> page = alumniReqOrClaimService.findAlumniReqOrClaims(assignee, requestedBy, pageable);
	HttpHeaders headers = PaginationUtil
		.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
	return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /alumni-req-or-claims/:id} : get the "id" alumniReqOrClaim.
     *
     * @param id the id of the alumniReqOrClaimDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the alumniReqOrClaimDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/alumni-req-or-claims/{id}")
    public ResponseEntity<AlumniReqOrClaimDTO> getAlumniReqOrClaim(@PathVariable String id) {
	log.debug("REST request to get AlumniReqOrClaim : {}", id);
	Optional<AlumniReqOrClaimDTO> alumniReqOrClaimDTO = alumniReqOrClaimService.findOne(id);
	return ResponseUtil.wrapOrNotFound(alumniReqOrClaimDTO);
    }

    /**
     * {@code DELETE  /alumni-req-or-claims/:id} : delete the "id" alumniReqOrClaim.
     *
     * @param id the id of the alumniReqOrClaimDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/alumni-req-or-claims/{id}")
    public ResponseEntity<Void> deleteAlumniReqOrClaim(@PathVariable String id) {
	log.debug("REST request to delete AlumniReqOrClaim : {}", id);
	alumniReqOrClaimService.delete(id);
	return ResponseEntity.noContent()
		.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
