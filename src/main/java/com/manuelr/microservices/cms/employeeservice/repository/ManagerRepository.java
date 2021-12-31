package com.manuelr.microservices.cms.employeeservice.repository;

import com.manuelr.microservices.cms.employeeservice.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends PersonRepository {

}
