package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.microservices.cms.employeeservice.entity.Employee;
import com.manuelr.microservices.cms.employeeservice.exception.EmailTakenException;
import com.manuelr.microservices.cms.employeeservice.exception.NotFoundException;
import com.manuelr.microservices.cms.employeeservice.repository.EmployeeRepository;
import com.manuelr.microservices.cms.employeeservice.service.CommissionsService;
import com.manuelr.microservices.cms.employeeservice.service.EmployeeService;
import com.manuelr.microservices.cms.employeeservice.web.assembler.EmployeeAssembler;
import com.manuelr.microservices.cms.employeeservice.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CommissionsService commissionsService;

    private final PagedResourcesAssembler<Employee> pagedResourcesAssembler;
    private final EmployeeAssembler employeeAssembler;

    private static final String EMAIL_TAKEN_ERROR_MSG = "Email %s has already taken";

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        if (employeeRepository.existsEmail(employeeDto.getEmail()))
            throw new EmailTakenException(String.format(EMAIL_TAKEN_ERROR_MSG, employeeDto.getEmail()));
        Employee employeeEntity = new Employee();
        Employee employee = employeeRepository.saveAndFlush(employeeEntity);
        log.info("Employee saved --> {}", employee);
        return employeeAssembler.toModel(employee);
    }

    @Override
    public EmployeeDto findEmployeeById(Long id) {
        Employee employee = (Employee) employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee was not found"));
        log.info("Employee founded --> {}", employee);
        EmployeeDto employeeDto = employeeAssembler.toModel(employee);
        employeeDto.setCommissions(commissionsService.findCommissionsByEmployeeId(employee.getId()));
        return employeeDto;
    }

    @Override
    public CollectionModel<EmployeeDto> findAllEmployees(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
//        Page<Employee> employees = employeeRepository.findAll(pageable);
        return pagedResourcesAssembler.toModel(null, employeeAssembler);
    }
}
