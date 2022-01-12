package com.manuelr.microservices.cms.employeeservice.service;

import com.manuelr.cms.commons.dto.PersonDto;
import org.springframework.security.access.prepost.PostAuthorize;

public interface PersonService extends GenericService<PersonDto> {
    @Override
    PersonDto findById(Long id);

    PersonDto findByUserId(Long id);

    PersonDto findByCurrentUser();
}
