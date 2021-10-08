package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.microservices.cms.employeeservice.dto.CommissionDto;
import com.manuelr.microservices.cms.employeeservice.entity.Employee;
import com.manuelr.microservices.cms.employeeservice.exception.EmailTakenException;
import com.manuelr.microservices.cms.employeeservice.exception.EmployeeNotFoundException;
import com.manuelr.microservices.cms.employeeservice.repository.EmployeeRepository;
import com.manuelr.microservices.cms.employeeservice.web.mapper.EmployeeMapper;
import com.manuelr.microservices.cms.employeeservice.service.CommissionsService;
import com.manuelr.microservices.cms.employeeservice.service.EmployeeService;
import com.manuelr.microservices.cms.employeeservice.web.assembler.EmployeeAssembler;
import com.manuelr.microservices.cms.employeeservice.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.hateoas.server.core.EmbeddedWrapper;
import org.springframework.hateoas.server.core.EmbeddedWrappers;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CommissionsService commissionsService;

    private final PagedResourcesAssembler<Employee> pagedResourcesAssembler;
    private final EmployeeAssembler employeeAssembler;
    private final EmployeeMapper employeeMapper;

    private static final String EMAIL_TAKEN_ERROR_MSG = "Email %s has already taken";

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        if (employeeRepository.existsEmail(employeeDto.getEmail()))
            throw new EmailTakenException(String.format(EMAIL_TAKEN_ERROR_MSG, employeeDto.getEmail()));
        Employee employeeEntity = employeeMapper.employeeDtoToEmployeeEntity(employeeDto);
        Employee employee = employeeRepository.saveAndFlush(employeeEntity);
        log.info("Employee saved --> {}", employee);
        return employeeAssembler.toModel(employee);
    }

    @Override
    public EmployeeDto findEmployeeById(Long id) {
        Employee employee = employeeRepository.findEmployeeEntityById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee was not found"));
        log.info("Employee founded --> {}", employee);
        EmployeeDto employeeDto = employeeAssembler.toModel(employee);
        employeeDto.setCommissions(commissionsService.findCommissionsByEmployeeId(employee.getId()));
        return employeeDto;
    }

    @Override
    public CollectionModel<EmployeeDto> findAllEmployees(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return pagedResourcesAssembler.toModel(employees, employeeAssembler);
    }
}
