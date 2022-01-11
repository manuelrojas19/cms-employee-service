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
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class FinanceAdminController {

    @Autowired
    @Qualifier("financeAdminServiceImpl")
    private PersonService financeAdminService;

    @GetMapping("/finance-admins")
    public ResponseEntity<CollectionModel<PersonDto>> findAll(
            @RequestParam(name = "page", required = false, defaultValue = Pagination.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = Pagination.DEFAULT_PAGE_SIZE) Integer size) {
        CollectionModel<PersonDto> response = financeAdminService.findAll(PageRequest.of(page, size));
        log.info("Sending to the client ---> {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/finance-admins/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable Long id) {
        PersonDto response = financeAdminService.findById(id);
        log.info("Sending to the client ---> {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
