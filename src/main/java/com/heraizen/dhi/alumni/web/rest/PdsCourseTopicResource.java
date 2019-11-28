package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.service.PdsCourseTopicService;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;
import com.heraizen.dhi.alumni.service.dto.PdsCourseTopicDTO;

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
 * REST controller for managing {@link com.heraizen.dhi.alumni.domain.PdsCourseTopic}.
 */
@RestController
@RequestMapping("/api")
public class PdsCourseTopicResource {

    private final Logger log = LoggerFactory.getLogger(PdsCourseTopicResource.class);

    private static final String ENTITY_NAME = "pdsCourseTopic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PdsCourseTopicService pdsCourseTopicService;

    public PdsCourseTopicResource(PdsCourseTopicService pdsCourseTopicService) {
        this.pdsCourseTopicService = pdsCourseTopicService;
    }

    /**
     * {@code POST  /pds-course-topics} : Create a new pdsCourseTopic.
     *
     * @param pdsCourseTopicDTO the pdsCourseTopicDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pdsCourseTopicDTO, or with status {@code 400 (Bad Request)} if the pdsCourseTopic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pds-course-topics")
    public ResponseEntity<PdsCourseTopicDTO> createPdsCourseTopic(@Valid @RequestBody PdsCourseTopicDTO pdsCourseTopicDTO) throws URISyntaxException {
        log.debug("REST request to save PdsCourseTopic : {}", pdsCourseTopicDTO);
        if (pdsCourseTopicDTO.getId() != null) {
            throw new BadRequestAlertException("A new pdsCourseTopic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PdsCourseTopicDTO result = pdsCourseTopicService.save(pdsCourseTopicDTO);
        return ResponseEntity.created(new URI("/api/pds-course-topics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pds-course-topics} : Updates an existing pdsCourseTopic.
     *
     * @param pdsCourseTopicDTO the pdsCourseTopicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pdsCourseTopicDTO,
     * or with status {@code 400 (Bad Request)} if the pdsCourseTopicDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pdsCourseTopicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pds-course-topics")
    public ResponseEntity<PdsCourseTopicDTO> updatePdsCourseTopic(@Valid @RequestBody PdsCourseTopicDTO pdsCourseTopicDTO) throws URISyntaxException {
        log.debug("REST request to update PdsCourseTopic : {}", pdsCourseTopicDTO);
        if (pdsCourseTopicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PdsCourseTopicDTO result = pdsCourseTopicService.save(pdsCourseTopicDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pdsCourseTopicDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pds-course-topics} : get all the pdsCourseTopics.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pdsCourseTopics in body.
     */
    @GetMapping("/pds-course-topics")
    public ResponseEntity<List<PdsCourseTopicDTO>> getAllPdsCourseTopics(Pageable pageable) {
        log.debug("REST request to get a page of PdsCourseTopics");
        Page<PdsCourseTopicDTO> page = pdsCourseTopicService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pds-course-topics/:id} : get the "id" pdsCourseTopic.
     *
     * @param id the id of the pdsCourseTopicDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pdsCourseTopicDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pds-course-topics/{id}")
    public ResponseEntity<PdsCourseTopicDTO> getPdsCourseTopic(@PathVariable String id) {
        log.debug("REST request to get PdsCourseTopic : {}", id);
        Optional<PdsCourseTopicDTO> pdsCourseTopicDTO = pdsCourseTopicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pdsCourseTopicDTO);
    }

    /**
     * {@code DELETE  /pds-course-topics/:id} : delete the "id" pdsCourseTopic.
     *
     * @param id the id of the pdsCourseTopicDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pds-course-topics/{id}")
    public ResponseEntity<Void> deletePdsCourseTopic(@PathVariable String id) {
        log.debug("REST request to delete PdsCourseTopic : {}", id);
        pdsCourseTopicService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
