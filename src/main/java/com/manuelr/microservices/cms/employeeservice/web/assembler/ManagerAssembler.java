package com.manuelr.microservices.cms.employeeservice.web.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.manuelr.microservices.cms.employeeservice.dto.ManagerDto;
import com.manuelr.microservices.cms.employeeservice.dto.PersonDto;
import com.manuelr.microservices.cms.employeeservice.entity.Manager;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import com.manuelr.microservices.cms.employeeservice.web.mapper.ManagerMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ManagerAssembler extends PersonAssembler {
    private final ManagerMapper managerMapper;
    private final PagedResourcesAssembler<Person> pagedResourcesAssembler;

    public PersonDto toModel(Person entity) {
        ManagerDto dto = managerMapper.managerToManagerDto((Manager) entity);
        return dto;
    }

    @Override
    public CollectionModel<PersonDto> toCollectionModel(Iterable<? extends Person> entities) {
        return pagedResourcesAssembler.toModel((Page<Person>) entities, this);
    }
}
