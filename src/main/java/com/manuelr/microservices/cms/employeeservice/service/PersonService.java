package com.manuelr.microservices.cms.employeeservice.service;

import com.manuelr.cms.commons.dto.PersonDto;
import com.manuelr.cms.commons.event.RegistrationEvent;
import com.manuelr.cms.commons.event.SignupEvent;

public interface PersonService extends GenericService<PersonDto> {

    PersonDto findByUserId(Long id);

    PersonDto findByCurrentUser();

    RegistrationEvent newSignupEvent(SignupEvent signupEvent);
}
