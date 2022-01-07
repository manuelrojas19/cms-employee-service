package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.microservices.cms.employeeservice.exception.NotFoundException;
import com.manuelr.microservices.cms.employeeservice.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class GenericServiceImpl<DTO extends RepresentationModel<DTO>, E, R extends JpaRepository<E, Long>,
        RA extends RepresentationModelAssembler<E, DTO>> implements GenericService<DTO> {
    protected final R repository;
    protected final RA resourceAssembler;
    protected final PagedResourcesAssembler<E> pagedResourcesAssembler;

    public GenericServiceImpl(R repository,
                              RA resourceAssembler,
                              PagedResourcesAssembler<E> pagedResourcesAssembler) {
        this.repository = repository;
        this.resourceAssembler = resourceAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @Override
    @Transactional(readOnly = true)
    public DTO findById(Long id) {
        E entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Resource not found"));
        return resourceAssembler.toModel(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public CollectionModel<DTO> findAll(Pageable pageable) {
        Page<E> entityPage = repository.findAll(pageable);
        if (entityPage.isEmpty())
            throw new NotFoundException("Resources not found");
        log.info("Retrieve elements --> {}", entityPage.getContent());
        return pagedResourcesAssembler.toModel(entityPage, resourceAssembler);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id))
            throw new NotFoundException("Resource was not found");
        repository.deleteById(id);
    }
}
