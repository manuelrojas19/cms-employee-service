package com.manuelr.microservices.cms.employeeservice.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }
}
