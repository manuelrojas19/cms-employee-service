package com.manuelr.microservices.cms.employeeservice.repository;

import com.manuelr.microservices.cms.employeeservice.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends PersonRepository {
    @Query("SELECT e FROM Employee e")
    Page<Person> findAll(Pageable page);

    @Query("SELECT e FROM Employee e WHERE e.manager.id = :managerId")
    Page<Person> findAllByManagerId(Pageable page, @Param("managerId") Long managerId);

    @Query("SELECT e from Employee e WHERE e.id = :id")
    @PostAuthorize("hasAuthority('EMPLOYEE') or" +
            "(hasAuthority('MANAGER') and " +
            "returnObject.empty ? true : (returnObject.get()).manager.id == authentication.principal.personId)")
    Optional<Person> findById(@Param("id") Long id);

    @Query("SELECT e from Employee e WHERE e.userId = :id")
    @PostAuthorize("hasAuthority('EMPLOYEE') or" +
            "(hasAuthority('MANAGER') and " +
            "returnObject.empty ? true : (returnObject.get()).manager.id == authentication.principal.personId)")
    Optional<Person> findByUserId(@Param("id") Long id);

    @Query("SELECT e from Employee e WHERE e.email = :email")
    @PostAuthorize("hasAuthority('EMPLOYEE') or" +
            "(hasAuthority('MANAGER') and " +
            "returnObject.empty ? true : (returnObject.get()).manager.id == authentication.principal.personId)")
    Optional<Person> findByEmail(@Param("email") String email);
}
