package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.cms.commons.dto.PersonDto;

import com.manuelr.cms.commons.event.registration.RegistrationStatus;
import com.manuelr.cms.commons.event.registration.RegistrationEvent;
import com.manuelr.cms.commons.event.signup.SignupEvent;
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

    public Person save(PersonDto personDto) {
        Person person = personMapper.dtoToEntity(personDto);
        return repository.save(person);
    }

    @Override
    @Transactional
    public RegistrationEvent newSignupEvent(SignupEvent signupEvent) {
        log.info("Signup event, person data ---> {}", signupEvent.getSignupRequestDto().getPersonData());
        PersonDto personDto = signupEvent.getSignupRequestDto().getPersonData();
        Person personSaved;
        try {
            personSaved = this.save(personDto);
        } catch (Exception e) {
            RegistrationEvent event = new RegistrationEvent(personDto, RegistrationStatus.FAILURE);
            log.info("Sending event ---> {}", event);
            return event;
        }
        log.info("personSaved --> {}", personSaved);
        personDto.setId(personSaved.getId());
        RegistrationEvent event = new RegistrationEvent(personDto, RegistrationStatus.SUCCESS);

        log.info("Sending event ---> {}", event);
        return event;
    }


}
