package com.manuelr.microservices.cms.employeeservice.web.assembler;

import com.manuelr.cms.commons.dto.EmployeeDto;
import com.manuelr.microservices.cms.employeeservice.controller.EmployeeController;
import com.manuelr.microservices.cms.employeeservice.entity.Employee;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import com.manuelr.microservices.cms.employeeservice.web.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class EmployeeAssembler implements PersonAssembler {
    private final EmployeeMapper mapper;

    public @NonNull com.manuelr.cms.commons.dto.PersonDto toModel(@NonNull Person entity) {
        EmployeeDto dto = mapper.entityToDto((Employee) entity);
        dto.add(linkTo(methodOn(EmployeeController.class).findById(entity.getId())).withSelfRel());
        dto.add(linkTo(methodOn(EmployeeController.class).findAll(null, null)).withRel("employees").expand());
        // TODO add manager to request
//        dto.add(linkTo(methodOn(ManagerController.class).findById(((Employee) entity).getManager().getId()))
//                .withRel("employeeManager"));
        return dto;
    }
}
