package com.manuelr.microservices.cms.employeeservice.config;

import com.manuelr.cms.commons.enums.Role;
import com.manuelr.cms.commons.event.registration.RegistrationEvent;
import com.manuelr.cms.commons.event.signup.SignupEvent;
import com.manuelr.microservices.cms.employeeservice.exception.BadRequestException;
import com.manuelr.microservices.cms.employeeservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class RegistrationConfig {

    @Autowired
    @Qualifier("employeeServiceImpl")
    private PersonService employeeService;

    @Autowired
    @Qualifier("managerServiceImpl")
    private PersonService managerService;

    @Bean
    public Function<Flux<SignupEvent>, Flux<RegistrationEvent>> registrationProcessor() {
        return signupEventFlux -> signupEventFlux.flatMap(this::processRegistration);

    }

    private Mono<RegistrationEvent> processRegistration(SignupEvent signupEvent) {
        if (signupEvent.getSignupRequestDto().getRole().equals(Role.EMPLOYEE))
            return Mono.fromSupplier(() -> this.employeeService.newSignupEvent(signupEvent));
        else if (signupEvent.getSignupRequestDto().getRole().equals(Role.MANAGER))
            return Mono.fromSupplier(() -> this.managerService.newSignupEvent(signupEvent));
        else
            throw new BadRequestException("No role founded");
    }

}
