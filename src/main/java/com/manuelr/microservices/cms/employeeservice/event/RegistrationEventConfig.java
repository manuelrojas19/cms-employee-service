package com.manuelr.microservices.cms.employeeservice.event;

import com.manuelr.cms.commons.event.registration.RegistrationEvent;
import com.manuelr.cms.commons.event.signup.SignupEvent;
import com.manuelr.microservices.cms.employeeservice.event.handler.SignupEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class RegistrationEventConfig {

    @Autowired
    private SignupEventHandler signupEventHandler;

    @Bean
    public Function<Flux<SignupEvent>, Flux<RegistrationEvent>> registrationProcessor() {
        return signupEventFlux -> signupEventFlux.flatMap(this::processRegistration);
    }

    private Mono<RegistrationEvent> processRegistration(SignupEvent signupEvent) {
        return Mono.fromSupplier(() -> this.signupEventHandler.handleSignup(signupEvent));
    }
}
