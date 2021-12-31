package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.microservices.cms.employeeservice.exception.NotFoundException;
import com.manuelr.microservices.cms.employeeservice.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public class GenericServiceImpl<DTO extends RepresentationModel<DTO>, E, R extends JpaRepository<E, Long>,
        RA extends RepresentationModelAssembler<E, DTO>> implements GenericService<DTO> {
    protected final R repository;
    protected final RA resourceAssembler;

    public GenericServiceImpl(R repository, RA resourceAssembler) {
        this.repository = repository;
        this.resourceAssembler = resourceAssembler;
    }

    @Override
    public DTO findById(Long id) {
        E entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Resource not found"));
        return resourceAssembler.toModel(entity);
    }

    @Override
    public CollectionModel<DTO> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<E> entityPage = repository.findAll(pageable);
        if (entityPage.isEmpty())
            throw new NotFoundException("Resources not found");
        return resourceAssembler.toCollectionModel(entityPage);
    }

    @Override
    public DTO save(DTO e) {
        // TODO add mapper to save entity
        return null;
    }


    @Override
    public void deleteById(Long id) {
        E entityToDelete = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Resource not found"));
        repository.delete(entityToDelete);
    }
}
