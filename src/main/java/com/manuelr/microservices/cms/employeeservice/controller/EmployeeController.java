package com.manuelr.microservices.cms.employeeservice.controller;

import com.manuelr.cms.commons.dto.PersonDto;
import com.manuelr.microservices.cms.employeeservice.constants.Pagination;
import com.manuelr.microservices.cms.employeeservice.service.EmployeeService;
import com.manuelr.microservices.cms.employeeservice.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    @Qualifier("employeeServiceImpl")
    private PersonService employeeService;

    // TODO restrict to only retrieve subordinates from manager
    @PreAuthorize("hasAuthority('FINANCE') or hasAuthority('MANAGER')")
    @GetMapping("/employees")
    public ResponseEntity<CollectionModel<PersonDto>> findAll(
            @RequestParam(name = "page", required = false, defaultValue = Pagination.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = Pagination.DEFAULT_PAGE_SIZE) Integer size) {
        CollectionModel<PersonDto> response = employeeService.findAll(PageRequest.of(page, size));
        log.info("Sending to the client ---> {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("(hasAuthority('MANAGER') and #managerId == authentication.principal.personId)" +
            "or hasAuthority('FINANCE')")
    @GetMapping("/managers/{managerId}/employees")
    public ResponseEntity<CollectionModel<PersonDto>> findAllByManagerId(
            @RequestParam(name = "page", required = false, defaultValue = Pagination.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = Pagination.DEFAULT_PAGE_SIZE) Integer size,
            @PathVariable Long managerId) {
        CollectionModel<PersonDto> response = ((EmployeeService) employeeService)
                .findAllByManagerId(managerId, PageRequest.of(page, size));
        log.info("Sending to the client ---> {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("(hasAuthority('EMPLOYEE') and #id == authentication.principal.personId)" +
            "or hasAuthority('MANAGER') or hasAuthority('FINANCE')")
    @GetMapping("/employees/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable Long id) {
        PersonDto response = employeeService.findById(id);
        log.info("Sending to the client ---> {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @GetMapping("/employees/current")
    public ResponseEntity<PersonDto> findCurrentEmployeeUser() {
        PersonDto response = employeeService.findByCurrentUser();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('FINANCE') " +
            "or hasAuthority('MANAGER') " +
            "or (hasAuthority('EMPLOYEE') and #id == authentication.principal.userId)")
    @GetMapping("/employees/findByUserId")
    public ResponseEntity<PersonDto> findByUserId(@RequestParam Long userId) {
        PersonDto response = employeeService.findByUserId(userId);
        log.info("Sending to the client ---> {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
