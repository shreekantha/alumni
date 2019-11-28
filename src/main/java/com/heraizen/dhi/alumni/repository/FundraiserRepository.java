package com.heraizen.dhi.alumni.repository;
import com.heraizen.dhi.alumni.domain.Fundraiser;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Fundraiser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FundraiserRepository extends MongoRepository<Fundraiser, String> {

}
