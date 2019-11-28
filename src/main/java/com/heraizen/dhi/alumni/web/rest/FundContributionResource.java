package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.service.FundContributionService;
import com.heraizen.dhi.alumni.web.rest.errors.BadRequestAlertException;
import com.heraizen.dhi.alumni.service.dto.FundContributionDTO;
import com.heraizen.dhi.alumni.service.util.DhiHeaderUtil;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

import org.bson.types.ObjectId;
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
 * {@link com.heraizen.dhi.alumni.domain.FundContribution}.
 */
@RestController
@RequestMapping("/api")
public class FundContributionResource {

    private final Logger log = LoggerFactory.getLogger(FundContributionResource.class);

    private static final String ENTITY_NAME = "fundContribution";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FundContributionService fundContributionService;

    public FundContributionResource(FundContributionService fundContributionService) {
	this.fundContributionService = fundContributionService;
    }

    /**
     * {@code POST  /fund-contributions} : Create a new fundContribution.
     *
     * @param fundContributionDTO the fundContributionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new fundContributionDTO, or with status
     *         {@code 400 (Bad Request)} if the fundContribution has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fund-contributions")
    public ResponseEntity<FundContributionDTO> createFundContribution(
	    @Valid @RequestBody FundContributionDTO fundContributionDTO) throws URISyntaxException {
	log.debug("REST request to save FundContribution : {}", fundContributionDTO);
	if (fundContributionDTO.getId() != null) {
	    throw new BadRequestAlertException("A new fundContribution cannot already have an ID", ENTITY_NAME,
		    "idexists");
	}
	FundContributionDTO result = fundContributionService.save(fundContributionDTO);
	return ResponseEntity
		.created(new URI("/api/fund-contributions/" + result.getId())).headers(DhiHeaderUtil
			.createAlert(applicationName, "Thank you so much for your contribution. This will make a difference",result.getId().toString()))
		.body(result);
    }

    /**
     * {@code PUT  /fund-contributions} : Updates an existing fundContribution.
     *
     * @param fundContributionDTO the fundContributionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated fundContributionDTO, or with status
     *         {@code 400 (Bad Request)} if the fundContributionDTO is not valid, or
     *         with status {@code 500 (Internal Server Error)} if the
     *         fundContributionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fund-contributions")
    public ResponseEntity<FundContributionDTO> updateFundContribution(
	    @Valid @RequestBody FundContributionDTO fundContributionDTO) throws URISyntaxException {
	log.debug("REST request to update FundContribution : {}", fundContributionDTO);
	if (fundContributionDTO.getId() == null) {
	    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
	}
	FundContributionDTO result = fundContributionService.save(fundContributionDTO);
	return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
		fundContributionDTO.getId().toString())).body(result);
    }

    /**
     * {@code GET  /fund-contributions} : get all the fundContributions.
     *
     * 
     * @param pageable the pagination information.
     * 
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of fundContributions in body.
     */
    @GetMapping("/fund-contributions")
    public ResponseEntity<List<FundContributionDTO>> getAllFundContributions(
	    @RequestParam(value = "contributor-id", required = false) ObjectId contributorId,
	    @RequestParam(value = "fundraiser-id", required = false) ObjectId fundraiserId, Pageable pageable) {
	log.debug("REST request to get a page of FundContributions");
	Page<FundContributionDTO> page = fundContributionService.findMyContributions(fundraiserId, contributorId,
		pageable);
	HttpHeaders headers = PaginationUtil
		.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
	return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fund-contributions/:id} : get the "id" fundContribution.
     *
     * @param id the id of the fundContributionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the fundContributionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fund-contributions/{id}")
    public ResponseEntity<FundContributionDTO> getFundContribution(@PathVariable String id) {
	log.debug("REST request to get FundContribution : {}", id);
	Optional<FundContributionDTO> fundContributionDTO = fundContributionService.findOne(id);
	return ResponseUtil.wrapOrNotFound(fundContributionDTO);
    }

    /**
     * {@code DELETE  /fund-contributions/:id} : delete the "id" fundContribution.
     *
     * @param id the id of the fundContributionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fund-contributions/{id}")
    public ResponseEntity<Void> deleteFundContribution(@PathVariable String id) {
	log.debug("REST request to delete FundContribution : {}", id);
	fundContributionService.delete(id);
	return ResponseEntity.noContent()
		.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
