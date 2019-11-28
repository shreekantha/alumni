package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.service.FundraiserService;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;
import com.heraizen.dhi.alumni.service.dto.FundraiserDTO;

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
 * REST controller for managing {@link com.heraizen.dhi.alumni.domain.Fundraiser}.
 */
@RestController
@RequestMapping("/api")
public class FundraiserResource {

    private final Logger log = LoggerFactory.getLogger(FundraiserResource.class);

    private static final String ENTITY_NAME = "fundraiser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FundraiserService fundraiserService;

    public FundraiserResource(FundraiserService fundraiserService) {
        this.fundraiserService = fundraiserService;
    }

    /**
     * {@code POST  /fundraisers} : Create a new fundraiser.
     *
     * @param fundraiserDTO the fundraiserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fundraiserDTO, or with status {@code 400 (Bad Request)} if the fundraiser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fundraisers")
    public ResponseEntity<FundraiserDTO> createFundraiser(@Valid @RequestBody FundraiserDTO fundraiserDTO) throws URISyntaxException {
        log.debug("REST request to save Fundraiser : {}", fundraiserDTO);
        if (fundraiserDTO.getId() != null) {
            throw new BadRequestAlertException("A new fundraiser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FundraiserDTO result = fundraiserService.save(fundraiserDTO);
        return ResponseEntity.created(new URI("/api/fundraisers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getName().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fundraisers} : Updates an existing fundraiser.
     *
     * @param fundraiserDTO the fundraiserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundraiserDTO,
     * or with status {@code 400 (Bad Request)} if the fundraiserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fundraiserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fundraisers")
    public ResponseEntity<FundraiserDTO> updateFundraiser(@Valid @RequestBody FundraiserDTO fundraiserDTO) throws URISyntaxException {
        log.debug("REST request to update Fundraiser : {}", fundraiserDTO);
        if (fundraiserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FundraiserDTO result = fundraiserService.save(fundraiserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fundraiserDTO.getName().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fundraisers} : get all the fundraisers.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fundraisers in body.
     */
    @GetMapping("/fundraisers")
    public ResponseEntity<List<FundraiserDTO>> getAllFundraisers(Pageable pageable) {
        log.debug("REST request to get a page of Fundraisers");
        Page<FundraiserDTO> page = fundraiserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fundraisers/:id} : get the "id" fundraiser.
     *
     * @param id the id of the fundraiserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fundraiserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fundraisers/{id}")
    public ResponseEntity<FundraiserDTO> getFundraiser(@PathVariable String id) {
        log.debug("REST request to get Fundraiser : {}", id);
        Optional<FundraiserDTO> fundraiserDTO = fundraiserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fundraiserDTO);
    }

    /**
     * {@code DELETE  /fundraisers/:id} : delete the "id" fundraiser.
     *
     * @param id the id of the fundraiserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fundraisers/{id}")
    public ResponseEntity<Void> deleteFundraiser(@PathVariable String id) {
        log.debug("REST request to delete Fundraiser : {}", id);
        fundraiserService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
