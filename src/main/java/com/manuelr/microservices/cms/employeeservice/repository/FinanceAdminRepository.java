package com.manuelr.microservices.cms.employeeservice.repository;

import com.manuelr.microservices.cms.employeeservice.entity.FinanceAdmin;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FinanceAdminRepository extends PersonRepository {
    @Query("SELECT fa FROM FinanceAdmin fa")
    Page<Person> findAll(Pageable page);
}
