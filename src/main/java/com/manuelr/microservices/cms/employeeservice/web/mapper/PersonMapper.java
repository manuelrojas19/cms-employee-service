package com.manuelr.microservices.cms.employeeservice.web.mapper;

import com.manuelr.cms.commons.dto.PersonDto;
import com.manuelr.microservices.cms.employeeservice.entity.Person;

public interface PersonMapper<DTO extends PersonDto, E extends Person> {
    DTO entityToDto(E entity);
    E dtoToEntity(DTO dto);
}
