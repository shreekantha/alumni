package com.heraizen.dhi.alumni.repository;
import com.heraizen.dhi.alumni.domain.AlumniReqOrClaim;
import com.heraizen.dhi.alumni.service.dto.AlumniReqOrClaimDTO;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the AlumniReqOrClaim entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlumniReqOrClaimRepository extends MongoRepository<AlumniReqOrClaim, String> {

    Page<AlumniReqOrClaim> findByAssigneeId(String assignee, Pageable pageable);

    Page<AlumniReqOrClaim> findByRequestById(String requestedBy, Pageable pageable);

}
