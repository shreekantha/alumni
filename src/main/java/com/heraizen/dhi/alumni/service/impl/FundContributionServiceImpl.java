package com.heraizen.dhi.alumni.service.impl;

import com.heraizen.dhi.alumni.service.FundContributionService;
import com.heraizen.dhi.alumni.domain.FundContribution;
import com.heraizen.dhi.alumni.domain.Fundraiser;
import com.heraizen.dhi.alumni.domain.enumeration.FundraiseStatus;
import com.heraizen.dhi.alumni.repository.FundContributionRepository;
import com.heraizen.dhi.alumni.repository.FundraiserRepository;
import com.heraizen.dhi.alumni.security.SecurityUtils;
import com.heraizen.dhi.alumni.service.dto.FundContributionDTO;
import com.heraizen.dhi.alumni.service.mapper.FundContributionMapper;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FundContribution}.
 */
@Service
public class FundContributionServiceImpl implements FundContributionService {

    private final Logger log = LoggerFactory.getLogger(FundContributionServiceImpl.class);

    private final FundContributionRepository fundContributionRepository;
    private final FundraiserRepository fundraiserRepository;
    private final FundContributionMapper fundContributionMapper;

    public FundContributionServiceImpl(FundContributionRepository fundContributionRepository,
	    FundContributionMapper fundContributionMapper, FundraiserRepository fundraiserServiceImpl) {
	this.fundContributionRepository = fundContributionRepository;
	this.fundContributionMapper = fundContributionMapper;
	this.fundraiserRepository = fundraiserServiceImpl;
    }

    /**
     * Save a fundContribution.
     *
     * @param fundContributionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FundContributionDTO save(FundContributionDTO fundContributionDTO) {
	log.debug("Request to save FundContribution : {}", fundContributionDTO.getFundraiserId());
	FundContribution fundContribution = fundContributionMapper.toEntity(fundContributionDTO);
	fundContribution.setContrDate(Instant.now());
	Fundraiser fundraiser = fundraiserRepository.findById(fundContributionDTO.getFundraiserId()).get();
	log.debug("fundraiser {}", fundraiser);

	Double collectedAmt = fundraiser.getCollectedAmount() != null
		? fundraiser.getCollectedAmount() + fundContribution.getContrAmount()
		: fundContribution.getContrAmount();
	fundraiser.setCollectedAmount(collectedAmt);
	if (fundraiser.getCollectedAmount() >= fundraiser.getTargetAmount())
	    fundraiser.setStatus(FundraiseStatus.ACHIEVED);
	fundraiserRepository.save(fundraiser);
	fundContribution = fundContributionRepository.save(fundContribution);
	return fundContributionMapper.toDto(fundContribution);
    }

    /**
     * Get all the fundContributions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<FundContributionDTO> findAll(Pageable pageable) {
	log.debug("Request to get all FundContributions");
	return fundContributionRepository.findAll(pageable).map(fundContributionMapper::toDto);
    }

    /**
     * Get all my Contributions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<FundContributionDTO> findMyContributions(ObjectId fundraiserId, ObjectId contributorId,
	    Pageable pageable) {
	log.debug("Request to get all FundContributions {}", contributorId);
//	String userLogin = SecurityUtils.getCurrentUserLogin()
//		.orElseThrow(() -> new IllegalArgumentException("User is not loged in to access"));
	if (fundraiserId != null && contributorId != null)
	    return fundContributionRepository.findMyContributionsByFundraiser(fundraiserId, contributorId, pageable)
		    .map(fundContributionMapper::toDto);
	if (fundraiserId != null)
	    return fundContributionRepository.findContributionsByFundraiser(fundraiserId, pageable)
		    .map(fundContributionMapper::toDto);
	if (contributorId != null)
	    return fundContributionRepository.findMyContrinutions(contributorId, pageable)
		    .map(fundContributionMapper::toDto);
	return fundContributionRepository.findAll(pageable).map(fundContributionMapper::toDto);
    }

    /**
     * Get one fundContribution by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<FundContributionDTO> findOne(String id) {
	log.debug("Request to get FundContribution : {}", id);
	return fundContributionRepository.findById(id).map(fundContributionMapper::toDto);
    }

    /**
     * Delete the fundContribution by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
	log.debug("Request to delete FundContribution : {}", id);
	fundContributionRepository.deleteById(id);
    }
}
