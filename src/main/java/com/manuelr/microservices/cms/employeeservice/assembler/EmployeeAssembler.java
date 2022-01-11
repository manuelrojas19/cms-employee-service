package com.manuelr.microservices.cms.employeeservice.assembler;

import com.manuelr.cms.commons.dto.EmployeeDto;
import com.manuelr.microservices.cms.employeeservice.controller.EmployeeController;
import com.manuelr.microservices.cms.employeeservice.controller.ManagerController;
import com.manuelr.microservices.cms.employeeservice.entity.Employee;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import com.manuelr.microservices.cms.employeeservice.mapper.EmployeeMapper;
import com.manuelr.microservices.cms.employeeservice.proxy.CommissionsServiceFeignClient;
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
        dto.add(linkTo(methodOn(EmployeeController.class).findAll(0, 8)).withRel("employees"));
        dto.add(linkTo(methodOn(ManagerController.class).findById(((Employee) entity).getManager().getId()))
                .withRel("employeeManager"));
        dto.add(linkTo(methodOn(CommissionsServiceFeignClient.class)
                .findCommissionsByEmployeeId(entity.getId())).withRel("employeeCommissions"));
        return dto;
    }
}
