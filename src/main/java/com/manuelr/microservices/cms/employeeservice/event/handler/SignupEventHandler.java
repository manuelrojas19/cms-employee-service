package com.manuelr.microservices.cms.employeeservice.event.handler;

import com.manuelr.cms.commons.dto.PersonDto;
import com.manuelr.cms.commons.enums.Role;
import com.manuelr.cms.commons.event.registration.RegistrationEvent;
import com.manuelr.cms.commons.event.registration.RegistrationStatus;
import com.manuelr.cms.commons.event.signup.SignupEvent;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import com.manuelr.microservices.cms.employeeservice.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class SignupEventHandler {

    @Autowired
    @Qualifier("employeeServiceImpl")
    private PersonService employeeService;

    @Autowired
    @Qualifier("managerServiceImpl")
    private PersonService managerService;

    @Transactional
    public RegistrationEvent handleSignup(SignupEvent signupEvent) {
        log.info("Signup event, person data ---> {}", signupEvent.getSignupRequestDto().getPersonData());
        PersonDto personDto = signupEvent.getSignupRequestDto().getPersonData();

        Person personSaved = null;

        if (signupEvent.getSignupRequestDto().getRole().equals(Role.EMPLOYEE))
            personSaved = employeeService.save(personDto);
        else if (signupEvent.getSignupRequestDto().getRole().equals(Role.MANAGER))
            personSaved = managerService.save(personDto);

        if (personSaved == null) {
            RegistrationEvent event = new RegistrationEvent(personDto, RegistrationStatus.FAILURE);
            log.info("Sending event ---> {}", event);
            return event;
        }
        personDto.setId(personSaved.getId());
        RegistrationEvent event = new RegistrationEvent(personDto, RegistrationStatus.SUCCESS);
        log.info("Sending event ---> {}", event);
        return event;
    }
}
