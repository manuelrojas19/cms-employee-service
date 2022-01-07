package com.manuelr.microservices.cms.employeeservice.service;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;

public interface GenericService<DTO> {
    DTO findById(Long id);
    CollectionModel<DTO> findAll(Pageable pageable);
    void deleteById(Long id);
}
