package com.heraizen.dhi.alumni.repository;

import com.heraizen.dhi.alumni.domain.FundContribution;
import com.heraizen.dhi.alumni.service.dto.FundContributionDTO;

import org.springframework.data.mongodb.repository.Query;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the FundContribution entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FundContributionRepository extends MongoRepository<FundContribution, String> {

    @Query("{'contibutor.$id':?0}")
    Page<FundContribution> findMyContrinutions(ObjectId userLogin, Pageable pageable);

    @Query("{'fundraiser.$id':?0}")
    Page<FundContribution> findContributionsByFundraiser(ObjectId fundraiserId, Pageable pageable);

    @Query("{'fundraiser.$id':?0,'contibutor.$id':?1}")
    Page<FundContribution> findMyContributionsByFundraiser(ObjectId fundraiserId, ObjectId contributorId,
	    Pageable pageable);

}
