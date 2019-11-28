package com.heraizen.dhi.alumni.repository;
import com.heraizen.dhi.alumni.domain.ReqOrClaimSubject;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the ReqOrClaimSubject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReqOrClaimSubjectRepository extends MongoRepository<ReqOrClaimSubject, String> {

}
