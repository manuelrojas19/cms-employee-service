package com.manuelr.microservices.cms.employeeservice.assembler;

import com.manuelr.cms.commons.dto.PersonDto;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public interface PersonAssembler extends RepresentationModelAssembler<Person, PersonDto> {
}
