package com.manuelr.microservices.cms.employeeservice.web.assembler;

import com.manuelr.microservices.cms.employeeservice.entity.Person;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public interface PersonAssembler extends RepresentationModelAssembler<Person, com.manuelr.cms.commons.dto.PersonDto> {
}
