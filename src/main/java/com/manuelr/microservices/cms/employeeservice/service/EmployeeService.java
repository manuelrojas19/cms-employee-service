package com.manuelr.microservices.cms.employeeservice.service;

import com.manuelr.microservices.cms.employeeservice.dto.EmployeeDto;
import org.springframework.hateoas.CollectionModel;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto findEmployeeById(Long id);
    CollectionModel<EmployeeDto> findAllEmployees(Integer page, Integer size);
}
