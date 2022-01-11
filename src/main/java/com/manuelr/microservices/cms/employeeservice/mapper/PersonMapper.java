package com.manuelr.microservices.cms.employeeservice.mapper;

import com.manuelr.microservices.cms.employeeservice.entity.Person;

public interface PersonMapper<DTO extends com.manuelr.cms.commons.dto.PersonDto, E extends Person> {
    DTO entityToDto(E entity);
    E dtoToEntity(DTO dto);
}
