package com.manuelr.microservices.cms.employeeservice.config;

import com.manuelr.cms.commons.enums.Role;
import com.manuelr.cms.commons.event.RegistrationEvent;
import com.manuelr.cms.commons.event.SignupEvent;
import com.manuelr.microservices.cms.employeeservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class EventConsumerConfig {

    @Autowired
    @Qualifier("employeeServiceImpl")
    private PersonService employeeService;

    @Bean
    public Function<Flux<SignupEvent>, Flux<RegistrationEvent>> registrationProcessor() {
        return signupEventFlux -> signupEventFlux.flatMap(this::processRegistration);

    }

    private Mono<RegistrationEvent> processRegistration(SignupEvent signupEvent) {
        return Mono.fromSupplier(() -> this.employeeService.newSignupEvent(signupEvent));
    }
}
