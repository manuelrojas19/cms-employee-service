package com.manuelr.microservices.cms.employeeservice.repository;

import com.manuelr.microservices.cms.employeeservice.entity.Employee;
import com.manuelr.microservices.cms.employeeservice.entity.Manager;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends PersonRepository {
    @Query("SELECT m FROM Manager m")
    Page<Person> findAll(Pageable page);
}
