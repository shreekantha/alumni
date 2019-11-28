package com.heraizen.dhi.alumni.repository;
import com.heraizen.dhi.alumni.domain.ProfDevService;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the ProfDevService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfDevServiceRepository extends MongoRepository<ProfDevService, String> {

}
