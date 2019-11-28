package com.heraizen.dhi.alumni.service;

import com.heraizen.dhi.alumni.service.dto.FundContributionDTO;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.heraizen.dhi.alumni.domain.FundContribution}.
 */
public interface FundContributionService {

    /**
     * Save a fundContribution.
     *
     * @param fundContributionDTO the entity to save.
     * @return the persisted entity.
     */
    FundContributionDTO save(FundContributionDTO fundContributionDTO);

    /**
     * Get all the fundContributions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FundContributionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fundContribution.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FundContributionDTO> findOne(String id);

    /**
     * Delete the "id" fundContribution.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

   // Page<FundContributionDTO> findMyContributions(Pageable pageable);

  //  Page<FundContributionDTO> findMyContributions(String contributorId, Pageable pageable);

  //  Page<FundContributionDTO> findMyContributions(ObjectId contributorId, Pageable pageable);

    Page<FundContributionDTO> findMyContributions(ObjectId fundraiserId, ObjectId contributorId, Pageable pageable);

    //Page<FundContributionDTO> findMyContributions(Boolean contributorId, Pageable pageable);
}
