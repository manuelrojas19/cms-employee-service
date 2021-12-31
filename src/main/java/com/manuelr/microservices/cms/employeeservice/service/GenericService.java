package com.manuelr.microservices.cms.employeeservice.service;

import org.springframework.hateoas.CollectionModel;

public interface GenericService<DTO> {
    DTO findById(Long id);
    CollectionModel<DTO> findAll(Integer page, Integer size);
    DTO save(DTO dto);
    void deleteById(Long id);
}
