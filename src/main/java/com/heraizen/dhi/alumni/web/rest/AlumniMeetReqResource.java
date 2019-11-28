package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.service.AlumniMeetReqService;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;
import com.heraizen.dhi.alumni.service.dto.AlumniMeetReqDTO;

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
 * REST controller for managing
 * {@link com.heraizen.dhi.alumni.domain.AlumniMeetReq}.
 */
@RestController
@RequestMapping("/api")
public class AlumniMeetReqResource {

    private final Logger log = LoggerFactory.getLogger(AlumniMeetReqResource.class);

    private static final String ENTITY_NAME = "Alumni Meet Req";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlumniMeetReqService alumniMeetReqService;

    public AlumniMeetReqResource(AlumniMeetReqService alumniMeetReqService) {
	this.alumniMeetReqService = alumniMeetReqService;
    }

    /**
     * {@code POST  /alumni-meet-reqs} : Create a new alumniMeetReq.
     *
     * @param alumniMeetReqDTO the alumniMeetReqDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new alumniMeetReqDTO, or with status
     *         {@code 400 (Bad Request)} if the alumniMeetReq has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/alumni-meet-reqs")
    public ResponseEntity<AlumniMeetReqDTO> createAlumniMeetReq(@Valid @RequestBody AlumniMeetReqDTO alumniMeetReqDTO)
	    throws URISyntaxException {
	log.debug("REST request to save AlumniMeetReq : {}", alumniMeetReqDTO);
	if (alumniMeetReqDTO.getId() != null) {
	    throw new BadRequestAlertException("A new alumniMeetReq cannot already have an ID", ENTITY_NAME,
		    "idexists");
	}
	AlumniMeetReqDTO result = alumniMeetReqService.save(alumniMeetReqDTO);
	return ResponseEntity
		.created(new URI("/api/alumni-meet-reqs/" + result.getId())).headers(HeaderUtil
			.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getTopicName().toString()))
		.body(result);
    }

    /**
     * {@code PUT  /alumni-meet-reqs} : Updates an existing alumniMeetReq.
     *
     * @param alumniMeetReqDTO the alumniMeetReqDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated alumniMeetReqDTO, or with status
     *         {@code 400 (Bad Request)} if the alumniMeetReqDTO is not valid, or
     *         with status {@code 500 (Internal Server Error)} if the
     *         alumniMeetReqDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/alumni-meet-reqs")
    public ResponseEntity<AlumniMeetReqDTO> updateAlumniMeetReq(@Valid @RequestBody AlumniMeetReqDTO alumniMeetReqDTO)
	    throws URISyntaxException {
	log.debug("REST request to update AlumniMeetReq : {}", alumniMeetReqDTO);
	if (alumniMeetReqDTO.getId() == null) {
	    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
	}
	AlumniMeetReqDTO result = alumniMeetReqService.save(alumniMeetReqDTO);
	return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
		alumniMeetReqDTO.getId().toString())).body(result);
    }

    /**
     * {@code GET  /alumni-meet-reqs} : get all the alumniMeetReqs.
     *
     * 
     * @param pageable the pagination information.
     * 
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of alumniMeetReqs in body.
     */
    @GetMapping("/alumni-meet-reqs")
    public ResponseEntity<List<AlumniMeetReqDTO>> getAllAlumniMeetReqs(
	    @RequestParam(value = "requested-by", required = false) String requestedBy,
	    @RequestParam(value = "requested-to", required = false) String requestedTo, Pageable pageable) {
	log.debug("REST request to get a page of AlumniMeetReqs");
	Page<AlumniMeetReqDTO> page = alumniMeetReqService.findAlumniMeetRequests(requestedBy, requestedTo, pageable);
	HttpHeaders headers = PaginationUtil
		.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
	return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /alumni-meet-reqs/:id} : get the "id" alumniMeetReq.
     *
     * @param id the id of the alumniMeetReqDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the alumniMeetReqDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/alumni-meet-reqs/{id}")
    public ResponseEntity<AlumniMeetReqDTO> getAlumniMeetReq(@PathVariable String id) {
	log.debug("REST request to get AlumniMeetReq : {}", id);
	Optional<AlumniMeetReqDTO> alumniMeetReqDTO = alumniMeetReqService.findOne(id);
	return ResponseUtil.wrapOrNotFound(alumniMeetReqDTO);
    }

    /**
     * {@code DELETE  /alumni-meet-reqs/:id} : delete the "id" alumniMeetReq.
     *
     * @param id the id of the alumniMeetReqDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/alumni-meet-reqs/{id}")
    public ResponseEntity<Void> deleteAlumniMeetReq(@PathVariable String id) {
	log.debug("REST request to delete AlumniMeetReq : {}", id);
	alumniMeetReqService.delete(id);
	return ResponseEntity.noContent()
		.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
