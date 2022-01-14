package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.cms.commons.dto.PersonDto;

import com.manuelr.cms.commons.enums.RegistrationStatus;
import com.manuelr.cms.commons.event.RegistrationEvent;
import com.manuelr.cms.commons.event.SignupEvent;
import com.manuelr.cms.commons.security.UserData;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import com.manuelr.microservices.cms.employeeservice.exception.NotFoundException;
import com.manuelr.microservices.cms.employeeservice.repository.PersonRepository;
import com.manuelr.microservices.cms.employeeservice.service.PersonService;
import com.manuelr.microservices.cms.employeeservice.web.assembler.PersonAssembler;
import com.manuelr.microservices.cms.employeeservice.web.mapper.PersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class PersonServiceImpl extends GenericServiceImpl<PersonDto, Person,
        PersonRepository, PersonAssembler> implements PersonService {

    private PersonMapper personMapper;

    public PersonServiceImpl(PersonRepository repository,
                             PersonAssembler resourceAssembler,
                             PagedResourcesAssembler<Person> pagedResourcesAssembler,
                             PersonMapper personMapper) {
        super(repository, resourceAssembler, pagedResourcesAssembler);
        this.personMapper = personMapper;
    }

    @Override
    public PersonDto findByUserId(Long id) {
        Person person = repository.findByUserId(id)
                .orElseThrow(() -> new NotFoundException("Person with user id not found"));
        return resourceAssembler.toModel(person);
    }

    @Override
    public PersonDto findByCurrentUser() {
        Long personId = ((UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        Person person = repository.findByUserId(personId).orElseThrow(() -> new NotFoundException("User was not found"));
        return resourceAssembler.toModel(person);
    }

    @Override
    @Transactional
    public RegistrationEvent newSignupEvent(SignupEvent signupEvent) {
        log.info("Signup event, person data ---> {}", signupEvent.getSignupRequestDto().getPersonData());
        PersonDto personDto = signupEvent.getSignupRequestDto().getPersonData();
        Person personSaved = repository.save(personMapper.dtoToEntity(personDto));
        log.info("personSaved --> {}", personSaved);
        personDto.setId(personSaved.getId());
        return new RegistrationEvent(personDto, RegistrationStatus.SUCCESS);
    }


}
