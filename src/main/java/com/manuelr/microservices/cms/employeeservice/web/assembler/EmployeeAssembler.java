package com.manuelr.microservices.cms.employeeservice.web.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.manuelr.microservices.cms.employeeservice.controller.EmployeeController;
import com.manuelr.microservices.cms.employeeservice.dto.EmployeeDto;
import com.manuelr.microservices.cms.employeeservice.entity.Employee;
import com.manuelr.microservices.cms.employeeservice.web.mapper.EmployeeMapper;
import com.manuelr.microservices.cms.employeeservice.web.proxy.CommissionsServiceFeignClient;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class EmployeeAssembler implements RepresentationModelAssembler<Employee, EmployeeDto> {
    private final EmployeeMapper employeeMapper;

    @Override
    public @NonNull EmployeeDto toModel(@NonNull Employee entity) {
        EmployeeDto employeeDto = employeeMapper.employeeEntityToEmployeeDto(entity);
        employeeDto.add(WebMvcLinkBuilder.linkTo(methodOn(EmployeeController.class).findEmployeeById(entity.getId())).withSelfRel());
        employeeDto.add(linkTo(methodOn(EmployeeController.class).findAllEmployees(0, 8)).withRel("employees"));
        employeeDto.add(WebMvcLinkBuilder.linkTo(methodOn(CommissionsServiceFeignClient.class)
                .findCommissionsByEmployeeId(entity.getId())).withRel("employeeCommissions"));
        return employeeDto;
    }
}
