package com.manuelr.microservices.cms.employeeservice.web.assembler;

import com.manuelr.cms.commons.dto.ManagerDto;
import com.manuelr.microservices.cms.employeeservice.controller.EmployeeController;
import com.manuelr.microservices.cms.employeeservice.controller.ManagerController;
import com.manuelr.microservices.cms.employeeservice.entity.Manager;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import com.manuelr.microservices.cms.employeeservice.web.mapper.ManagerMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class ManagerAssembler implements PersonAssembler {
    private final ManagerMapper mapper;

    public @NonNull com.manuelr.cms.commons.dto.PersonDto toModel(@NonNull Person entity) {
        ManagerDto dto = mapper.entityToDto((Manager) entity);
        dto.add(linkTo(methodOn(ManagerController.class).findById(entity.getId())).withSelfRel());
        dto.add(linkTo(methodOn(ManagerController.class).findAll(0, 8)).withRel("managers"));
        dto.add(linkTo(methodOn(EmployeeController.class).findAllByManagerId(0, 8, entity.getId()))
                .withRel("managerEmployees"));
        return dto;
    }

}
