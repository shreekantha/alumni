package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.service.MeetReqTopicService;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;
import com.heraizen.dhi.alumni.service.dto.MeetReqTopicDTO;

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
 * REST controller for managing {@link com.heraizen.dhi.alumni.domain.MeetReqTopic}.
 */
@RestController
@RequestMapping("/api")
public class MeetReqTopicResource {

    private final Logger log = LoggerFactory.getLogger(MeetReqTopicResource.class);

    private static final String ENTITY_NAME = "Meet Req Topic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MeetReqTopicService meetReqTopicService;

    public MeetReqTopicResource(MeetReqTopicService meetReqTopicService) {
        this.meetReqTopicService = meetReqTopicService;
    }

    /**
     * {@code POST  /meet-req-topics} : Create a new meetReqTopic.
     *
     * @param meetReqTopicDTO the meetReqTopicDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new meetReqTopicDTO, or with status {@code 400 (Bad Request)} if the meetReqTopic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/meet-req-topics")
    public ResponseEntity<MeetReqTopicDTO> createMeetReqTopic(@Valid @RequestBody MeetReqTopicDTO meetReqTopicDTO) throws URISyntaxException {
        log.debug("REST request to save MeetReqTopic : {}", meetReqTopicDTO);
        if (meetReqTopicDTO.getId() != null) {
            throw new BadRequestAlertException("A new meetReqTopic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MeetReqTopicDTO result = meetReqTopicService.save(meetReqTopicDTO);
        return ResponseEntity.created(new URI("/api/meet-req-topics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getName().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /meet-req-topics} : Updates an existing meetReqTopic.
     *
     * @param meetReqTopicDTO the meetReqTopicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated meetReqTopicDTO,
     * or with status {@code 400 (Bad Request)} if the meetReqTopicDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the meetReqTopicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/meet-req-topics")
    public ResponseEntity<MeetReqTopicDTO> updateMeetReqTopic(@Valid @RequestBody MeetReqTopicDTO meetReqTopicDTO) throws URISyntaxException {
        log.debug("REST request to update MeetReqTopic : {}", meetReqTopicDTO);
        if (meetReqTopicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MeetReqTopicDTO result = meetReqTopicService.save(meetReqTopicDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, meetReqTopicDTO.getName().toString()))
            .body(result);
    }

    /**
     * {@code GET  /meet-req-topics} : get all the meetReqTopics.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of meetReqTopics in body.
     */
    @GetMapping("/meet-req-topics")
    public ResponseEntity<List<MeetReqTopicDTO>> getAllMeetReqTopics(Pageable pageable) {
        log.debug("REST request to get a page of MeetReqTopics");
        Page<MeetReqTopicDTO> page = meetReqTopicService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /meet-req-topics/:id} : get the "id" meetReqTopic.
     *
     * @param id the id of the meetReqTopicDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the meetReqTopicDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/meet-req-topics/{id}")
    public ResponseEntity<MeetReqTopicDTO> getMeetReqTopic(@PathVariable String id) {
        log.debug("REST request to get MeetReqTopic : {}", id);
        Optional<MeetReqTopicDTO> meetReqTopicDTO = meetReqTopicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(meetReqTopicDTO);
    }

    /**
     * {@code DELETE  /meet-req-topics/:id} : delete the "id" meetReqTopic.
     *
     * @param id the id of the meetReqTopicDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/meet-req-topics/{id}")
    public ResponseEntity<Void> deleteMeetReqTopic(@PathVariable String id) {
        log.debug("REST request to delete MeetReqTopic : {}", id);
        meetReqTopicService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
