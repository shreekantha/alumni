package com.heraizen.dhi.alumni.repository;
import com.heraizen.dhi.alumni.domain.PdsSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the PdsSchedule entity.
 */
@Repository
public interface PdsScheduleRepository extends MongoRepository<PdsSchedule, String> {

    @Query("{}")
    Page<PdsSchedule> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<PdsSchedule> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<PdsSchedule> findOneWithEagerRelationships(String id);

}
