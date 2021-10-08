package com.manuelr.microservices.cms.employeeservice.repository;

import com.manuelr.microservices.cms.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeEntityById(Long id);

    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Employee s " +
            "WHERE s.email = ?1"
    )
    Boolean existsEmail(String email);
}
