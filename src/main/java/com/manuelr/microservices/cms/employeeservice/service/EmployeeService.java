package com.manuelr.microservices.cms.employeeservice.service;

import com.manuelr.cms.commons.dto.PersonDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;

public interface EmployeeService extends PersonService {
    CollectionModel<PersonDto> findAllByManagerId(Long managerId, Pageable pageable);
}
