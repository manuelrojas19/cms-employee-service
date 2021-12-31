package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.microservices.cms.employeeservice.dto.PersonDto;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import com.manuelr.microservices.cms.employeeservice.repository.PersonRepository;
import com.manuelr.microservices.cms.employeeservice.service.PersonService;
import com.manuelr.microservices.cms.employeeservice.web.assembler.PersonAssembler;

public class PersonServiceImpl extends GenericServiceImpl<PersonDto, Person,
        PersonRepository, PersonAssembler> implements PersonService {

    public PersonServiceImpl(PersonRepository repository, PersonAssembler resourceAssembler) {
        super(repository, resourceAssembler);
    }
}
