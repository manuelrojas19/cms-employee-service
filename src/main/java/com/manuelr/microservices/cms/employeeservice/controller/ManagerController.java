package com.manuelr.microservices.cms.employeeservice.controller;

import com.manuelr.cms.commons.dto.PersonDto;
import com.manuelr.microservices.cms.employeeservice.constants.Pagination;
import com.manuelr.microservices.cms.employeeservice.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/managers")
public class ManagerController {

    @Autowired
    @Qualifier("managerServiceImpl")
    private PersonService managerService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<CollectionModel<PersonDto>> findAll(
            @RequestParam(name = "page", required = false, defaultValue = Pagination.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = Pagination.DEFAULT_PAGE_SIZE) Integer size) {
        CollectionModel<PersonDto> response = managerService.findAll(PageRequest.of(page, size));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/current")
    public ResponseEntity<PersonDto> findCurrentManagerUser() {
        PersonDto response = managerService.findByCurrentUser();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("(hasAuthority('MANAGER') and #id == authentication.principal.personId)")
    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable Long id) {
        PersonDto response = managerService.findById(id);
        log.info("Sending to the client ---> {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
