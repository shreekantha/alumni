package com.heraizen.dhi.alumni.repository;

import com.heraizen.dhi.alumni.domain.Alumni;
import com.heraizen.dhi.alumni.service.dto.AlumniDTO;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Alumni entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlumniRepository extends MongoRepository<Alumni, String> {

    Page<Alumni> findAllByExperienceAndJobPositionAndYearOfGraduationAndSpecialization(Double experience, String position,
	    String yearOfGraduation, String department, Pageable pageable);

}
