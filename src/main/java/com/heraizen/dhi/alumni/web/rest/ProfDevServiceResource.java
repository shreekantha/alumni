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

import com.heraizen.dhi.alumni.service.ProfDevServiceService;
import com.heraizen.dhi.alumni.service.dto.ProfDevServiceDTO;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.heraizen.dhi.alumni.domain.ProfDevService}.
 */
@RestController
@RequestMapping("/api")
public class ProfDevServiceResource {

    private final Logger log = LoggerFactory.getLogger(ProfDevServiceResource.class);

    private static final String ENTITY_NAME = "Professional Development Service";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProfDevServiceService profDevServiceService;

    public ProfDevServiceResource(ProfDevServiceService profDevServiceService) {
        this.profDevServiceService = profDevServiceService;
    }

    /**
     * {@code POST  /prof-dev-services} : Create a new profDevService.
     *
     * @param profDevServiceDTO the profDevServiceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new profDevServiceDTO, or with status {@code 400 (Bad Request)} if the profDevService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prof-dev-services")
    public ResponseEntity<ProfDevServiceDTO> createProfDevService(@Valid @RequestBody ProfDevServiceDTO profDevServiceDTO) throws URISyntaxException {
        log.debug("REST request to save ProfDevService : {}", profDevServiceDTO);
        if (profDevServiceDTO.getId() != null) {
            throw new BadRequestAlertException("A new profDevService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfDevServiceDTO result = profDevServiceService.save(profDevServiceDTO);
        return ResponseEntity.created(new URI("/api/prof-dev-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getName().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prof-dev-services} : Updates an existing profDevService.
     *
     * @param profDevServiceDTO the profDevServiceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated profDevServiceDTO,
     * or with status {@code 400 (Bad Request)} if the profDevServiceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the profDevServiceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prof-dev-services")
    public ResponseEntity<ProfDevServiceDTO> updateProfDevService(@Valid @RequestBody ProfDevServiceDTO profDevServiceDTO) throws URISyntaxException {
        log.debug("REST request to update ProfDevService : {}", profDevServiceDTO);
        if (profDevServiceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfDevServiceDTO result = profDevServiceService.save(profDevServiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, profDevServiceDTO.getName().toString()))
            .body(result);
    }

    /**
     * {@code GET  /prof-dev-services} : get all the profDevServices.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of profDevServices in body.
     */
    @GetMapping("/prof-dev-services")
    public ResponseEntity<List<ProfDevServiceDTO>> getAllProfDevServices(Pageable pageable) {
        log.debug("REST request to get a page of ProfDevServices");
        Page<ProfDevServiceDTO> page = profDevServiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /prof-dev-services/:id} : get the "id" profDevService.
     *
     * @param id the id of the profDevServiceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the profDevServiceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prof-dev-services/{id}")
    public ResponseEntity<ProfDevServiceDTO> getProfDevService(@PathVariable String id) {
        log.debug("REST request to get ProfDevService : {}", id);
        Optional<ProfDevServiceDTO> profDevServiceDTO = profDevServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(profDevServiceDTO);
    }

    /**
     * {@code DELETE  /prof-dev-services/:id} : delete the "id" profDevService.
     *
     * @param id the id of the profDevServiceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prof-dev-services/{id}")
    public ResponseEntity<Void> deleteProfDevService(@PathVariable String id) {
        log.debug("REST request to delete ProfDevService : {}", id);
        profDevServiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
