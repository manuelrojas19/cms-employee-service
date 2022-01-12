package com.manuelr.microservices.cms.employeeservice.repository;

import com.manuelr.microservices.cms.employeeservice.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends PersonRepository {
    @Query("SELECT m FROM Manager m")
    Page<Person> findAll(Pageable page);
    @Query("SELECT m from Manager m WHERE m.id = :id")
    Optional<Person> findById(@Param("id") Long id);

}
