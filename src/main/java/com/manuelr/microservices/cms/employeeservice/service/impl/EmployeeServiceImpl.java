package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.cms.commons.dto.EmployeeDto;
import com.manuelr.cms.commons.dto.PersonDto;
import com.manuelr.cms.commons.event.registration.RegistrationEvent;
import com.manuelr.cms.commons.event.registration.RegistrationStatus;
import com.manuelr.cms.commons.event.signup.SignupEvent;
import com.manuelr.microservices.cms.employeeservice.entity.Employee;
import com.manuelr.microservices.cms.employeeservice.entity.Manager;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import com.manuelr.microservices.cms.employeeservice.exception.NotFoundException;
import com.manuelr.microservices.cms.employeeservice.repository.EmployeeRepository;
import com.manuelr.microservices.cms.employeeservice.repository.ManagerRepository;
import com.manuelr.microservices.cms.employeeservice.repository.PersonRepository;
import com.manuelr.microservices.cms.employeeservice.service.EmployeeService;
import com.manuelr.microservices.cms.employeeservice.web.assembler.PersonAssembler;
import com.manuelr.microservices.cms.employeeservice.web.mapper.EmployeeMapper;
import com.manuelr.microservices.cms.employeeservice.web.mapper.PersonMapper;
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

    private final PersonRepository managerRepository;
    private final PersonMapper mapper;

    public EmployeeServiceImpl(@Qualifier("employeeRepository") PersonRepository repository,
                               @Qualifier("managerRepository") PersonRepository managerRepository,
                               @Qualifier("employeeAssembler") PersonAssembler resourceAssembler,
                               PagedResourcesAssembler<Person> pagedResourcesAssembler,
                               EmployeeMapper mapper) {
        super(repository, resourceAssembler, pagedResourcesAssembler, mapper);
        this.managerRepository = managerRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public CollectionModel<PersonDto> findAllByManagerId(Long managerId, Pageable pageable) {
        Page<Person> employees = ((EmployeeRepository) repository).findAllByManagerId(pageable, managerId);
        log.info("Retrieved Data ---> {}", employees.getContent());
        if (employees.isEmpty())
            throw new NotFoundException(NOT_FOUND_ERROR_MSG);
        return resourceAssembler.toCollectionModel(employees);
    }
    
    @Override
    public Person save(PersonDto person) {
        EmployeeDto employeeDto = (EmployeeDto) person;
        Manager manager = (Manager) managerRepository.findById(employeeDto.getManagerId())
                .orElseThrow(() -> new NotFoundException("Manager not found"));
        Employee employeeToSave = (Employee) mapper.dtoToEntity(employeeDto);
        employeeToSave.setManager(manager);
        return repository.save(employeeToSave);
    }
}
