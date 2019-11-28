package com.heraizen.dhi.alumni.repository;
import com.heraizen.dhi.alumni.domain.AspiredRole;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the AspiredRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AspiredRoleRepository extends MongoRepository<AspiredRole, String> {

}
