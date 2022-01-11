package com.manuelr.microservices.cms.employeeservice.service;

import com.manuelr.cms.commons.dto.PersonDto;

public interface PersonService extends GenericService<PersonDto> {
    PersonDto findByUserId(Long id);
}
