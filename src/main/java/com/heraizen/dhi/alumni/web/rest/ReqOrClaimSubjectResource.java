package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.service.ReqOrClaimSubjectService;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;
import com.heraizen.dhi.alumni.service.dto.ReqOrClaimSubjectDTO;

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
 * REST controller for managing {@link com.heraizen.dhi.alumni.domain.ReqOrClaimSubject}.
 */
@RestController
@RequestMapping("/api")
public class ReqOrClaimSubjectResource {

    private final Logger log = LoggerFactory.getLogger(ReqOrClaimSubjectResource.class);

    private static final String ENTITY_NAME = "reqOrClaimSubject";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReqOrClaimSubjectService reqOrClaimSubjectService;

    public ReqOrClaimSubjectResource(ReqOrClaimSubjectService reqOrClaimSubjectService) {
        this.reqOrClaimSubjectService = reqOrClaimSubjectService;
    }

    /**
     * {@code POST  /req-or-claim-subjects} : Create a new reqOrClaimSubject.
     *
     * @param reqOrClaimSubjectDTO the reqOrClaimSubjectDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reqOrClaimSubjectDTO, or with status {@code 400 (Bad Request)} if the reqOrClaimSubject has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/req-or-claim-subjects")
    public ResponseEntity<ReqOrClaimSubjectDTO> createReqOrClaimSubject(@RequestBody ReqOrClaimSubjectDTO reqOrClaimSubjectDTO) throws URISyntaxException {
        log.debug("REST request to save ReqOrClaimSubject : {}", reqOrClaimSubjectDTO);
        if (reqOrClaimSubjectDTO.getId() != null) {
            throw new BadRequestAlertException("A new reqOrClaimSubject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReqOrClaimSubjectDTO result = reqOrClaimSubjectService.save(reqOrClaimSubjectDTO);
        return ResponseEntity.created(new URI("/api/req-or-claim-subjects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /req-or-claim-subjects} : Updates an existing reqOrClaimSubject.
     *
     * @param reqOrClaimSubjectDTO the reqOrClaimSubjectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reqOrClaimSubjectDTO,
     * or with status {@code 400 (Bad Request)} if the reqOrClaimSubjectDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reqOrClaimSubjectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/req-or-claim-subjects")
    public ResponseEntity<ReqOrClaimSubjectDTO> updateReqOrClaimSubject(@RequestBody ReqOrClaimSubjectDTO reqOrClaimSubjectDTO) throws URISyntaxException {
        log.debug("REST request to update ReqOrClaimSubject : {}", reqOrClaimSubjectDTO);
        if (reqOrClaimSubjectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReqOrClaimSubjectDTO result = reqOrClaimSubjectService.save(reqOrClaimSubjectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, reqOrClaimSubjectDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /req-or-claim-subjects} : get all the reqOrClaimSubjects.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reqOrClaimSubjects in body.
     */
    @GetMapping("/req-or-claim-subjects")
    public ResponseEntity<List<ReqOrClaimSubjectDTO>> getAllReqOrClaimSubjects(Pageable pageable) {
        log.debug("REST request to get a page of ReqOrClaimSubjects");
        Page<ReqOrClaimSubjectDTO> page = reqOrClaimSubjectService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /req-or-claim-subjects/:id} : get the "id" reqOrClaimSubject.
     *
     * @param id the id of the reqOrClaimSubjectDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reqOrClaimSubjectDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/req-or-claim-subjects/{id}")
    public ResponseEntity<ReqOrClaimSubjectDTO> getReqOrClaimSubject(@PathVariable String id) {
        log.debug("REST request to get ReqOrClaimSubject : {}", id);
        Optional<ReqOrClaimSubjectDTO> reqOrClaimSubjectDTO = reqOrClaimSubjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reqOrClaimSubjectDTO);
    }

    /**
     * {@code DELETE  /req-or-claim-subjects/:id} : delete the "id" reqOrClaimSubject.
     *
     * @param id the id of the reqOrClaimSubjectDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/req-or-claim-subjects/{id}")
    public ResponseEntity<Void> deleteReqOrClaimSubject(@PathVariable String id) {
        log.debug("REST request to delete ReqOrClaimSubject : {}", id);
        reqOrClaimSubjectService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
