package com.heraizen.dhi.alumni.repository;
import com.heraizen.dhi.alumni.domain.PdsEnrollment;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the PdsEnrollment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PdsEnrollmentRepository extends MongoRepository<PdsEnrollment, String> {

    Page<PdsEnrollment> findByLastModifiedBy(String userLogin,Pageable pageable);
    Page<PdsEnrollment> findByEnrolledByLogin(String userLogin,Pageable pageable);
    @Query("{'lastModifiedBy': ?0}")
    Page<PdsEnrollment> findAllWithEagerRelationships(String userLogin,Pageable pageable);
  Optional<PdsEnrollment> findByEnrolledToIdAndEnrolledBy(String enrolledTo, String string);

}
