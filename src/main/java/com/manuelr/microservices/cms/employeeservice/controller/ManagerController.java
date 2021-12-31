package com.manuelr.microservices.cms.employeeservice.controller;

import com.manuelr.microservices.cms.employeeservice.dto.PersonDto;
import com.manuelr.microservices.cms.employeeservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/managers")
public class ManagerController {
    @Autowired
    @Qualifier("managerServiceImpl")
    private PersonService managerService;

    private static final String DEFAULT_PAGE_NUMBER = "0";
    private static final String DEFAULT_PAGE_SIZE = "8";

    @GetMapping
    public ResponseEntity<CollectionModel<PersonDto>> findAll(
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        CollectionModel<PersonDto> response = managerService.findAll(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
