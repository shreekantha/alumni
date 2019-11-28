package com.heraizen.dhi.alumni.repository;
import com.heraizen.dhi.alumni.domain.PdsCourseTopic;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the PdsCourseTopic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PdsCourseTopicRepository extends MongoRepository<PdsCourseTopic, String> {

}
