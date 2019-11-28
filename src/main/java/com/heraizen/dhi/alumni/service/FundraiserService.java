package com.heraizen.dhi.alumni.service;

import com.heraizen.dhi.alumni.service.dto.FundraiserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.heraizen.dhi.alumni.domain.Fundraiser}.
 */
public interface FundraiserService {

    /**
     * Save a fundraiser.
     *
     * @param fundraiserDTO the entity to save.
     * @return the persisted entity.
     */
    FundraiserDTO save(FundraiserDTO fundraiserDTO);

    /**
     * Get all the fundraisers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FundraiserDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fundraiser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FundraiserDTO> findOne(String id);

    /**
     * Delete the "id" fundraiser.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
