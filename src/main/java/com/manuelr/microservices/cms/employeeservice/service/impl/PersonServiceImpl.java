package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.cms.commons.dto.PersonDto;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import com.manuelr.microservices.cms.employeeservice.repository.PersonRepository;
import com.manuelr.microservices.cms.employeeservice.service.PersonService;
import com.manuelr.microservices.cms.employeeservice.assembler.PersonAssembler;
import org.springframework.data.web.PagedResourcesAssembler;

public class PersonServiceImpl extends GenericServiceImpl<PersonDto, Person,
        PersonRepository, PersonAssembler> implements PersonService {

    public PersonServiceImpl(PersonRepository repository,
                             PersonAssembler resourceAssembler,
                             PagedResourcesAssembler<Person> pagedResourcesAssembler) {
        super(repository, resourceAssembler, pagedResourcesAssembler);
    }
}
