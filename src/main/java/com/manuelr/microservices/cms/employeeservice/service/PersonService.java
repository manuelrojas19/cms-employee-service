package com.manuelr.microservices.cms.employeeservice.service;

import com.manuelr.cms.commons.dto.PersonDto;
import com.manuelr.cms.commons.event.registration.RegistrationEvent;
import com.manuelr.cms.commons.event.signup.SignupEvent;
import com.manuelr.microservices.cms.employeeservice.entity.Person;

public interface PersonService extends GenericService<PersonDto> {

    PersonDto findByUserId(Long id);

    PersonDto findByCurrentUser();

    Person save(PersonDto person);

    RegistrationEvent newSignupEvent(SignupEvent signupEvent);
}
