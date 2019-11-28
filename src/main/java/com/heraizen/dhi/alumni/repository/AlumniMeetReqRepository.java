package com.heraizen.dhi.alumni.repository;
import com.heraizen.dhi.alumni.domain.AlumniMeetReq;
import com.heraizen.dhi.alumni.service.dto.AlumniMeetReqDTO;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the AlumniMeetReq entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlumniMeetReqRepository extends MongoRepository<AlumniMeetReq, String> {

    Page<AlumniMeetReq> findByRequestBy(String requestedBy, Pageable pageable);

    Page<AlumniMeetReq> findByRequestTo(String requestedTo, Pageable pageable);

    
}
