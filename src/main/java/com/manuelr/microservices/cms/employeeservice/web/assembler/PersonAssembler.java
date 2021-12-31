package com.manuelr.microservices.cms.employeeservice.web.assembler;

import com.manuelr.microservices.cms.employeeservice.dto.PersonDto;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersonAssembler implements RepresentationModelAssembler<Person, PersonDto> {

    @Override
    public PersonDto toModel(Person entity) {
        return null;
    }

    @Override
    public CollectionModel<PersonDto> toCollectionModel(Iterable<? extends Person> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

}
