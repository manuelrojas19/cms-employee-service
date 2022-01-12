package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.cms.commons.dto.PersonDto;

import com.manuelr.microservices.cms.employeeservice.entity.Person;
import com.manuelr.microservices.cms.employeeservice.exception.NotFoundException;
import com.manuelr.microservices.cms.employeeservice.repository.PersonRepository;
import com.manuelr.microservices.cms.employeeservice.service.PersonService;
import com.manuelr.microservices.cms.employeeservice.assembler.PersonAssembler;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.security.core.context.SecurityContextHolder;

public class PersonServiceImpl extends GenericServiceImpl<PersonDto, Person,
        PersonRepository, PersonAssembler> implements PersonService {

    public PersonServiceImpl(PersonRepository repository,
                             PersonAssembler resourceAssembler,
                             PagedResourcesAssembler<Person> pagedResourcesAssembler) {
        super(repository, resourceAssembler, pagedResourcesAssembler);
    }

    @Override
    public PersonDto findByUserId(Long id) {
        Person person = repository.findByUserId(id)
                .orElseThrow(() -> new NotFoundException("Person with user id not found"));
        return resourceAssembler.toModel(person);
    }

    @Override
    public PersonDto findByCurrentUser() {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Person person = repository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new NotFoundException("Person not found"));
        return resourceAssembler.toModel(person);
    }
}
