package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.microservices.cms.employeeservice.entity.Person;
import com.manuelr.microservices.cms.employeeservice.exception.NotFoundException;
import com.manuelr.microservices.cms.employeeservice.repository.EmployeeRepository;
import com.manuelr.microservices.cms.employeeservice.repository.PersonRepository;
import com.manuelr.microservices.cms.employeeservice.service.EmployeeService;
import com.manuelr.microservices.cms.employeeservice.assembler.PersonAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class EmployeeServiceImpl extends PersonServiceImpl implements EmployeeService {
    private static final String NOT_FOUND_ERROR_MSG = "Employees was not found";

    public EmployeeServiceImpl(@Qualifier("employeeRepository") PersonRepository repository,
                               @Qualifier("employeeAssembler") PersonAssembler resourceAssembler,
                               PagedResourcesAssembler<Person> pagedResourcesAssembler) {
        super(repository, resourceAssembler, pagedResourcesAssembler);
    }

    @Override
    @Transactional(readOnly = true)
    public CollectionModel<com.manuelr.cms.commons.dto.PersonDto> findAllByManagerId(Long managerId, Pageable pageable) {
        Page<Person> employees = ((EmployeeRepository) repository).findAllByManagerId(pageable, managerId);
        log.info("Retrieved Data ---> {}", employees.getContent());
        if (employees.isEmpty())
            throw new NotFoundException(NOT_FOUND_ERROR_MSG);
        return resourceAssembler.toCollectionModel(employees);
    }
}
