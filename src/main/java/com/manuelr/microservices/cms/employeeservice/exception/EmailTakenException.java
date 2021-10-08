package com.manuelr.microservices.cms.employeeservice.exception;

public class EmailTakenException extends RuntimeException {
    public EmailTakenException(String s) {
        super(s);
    }
}
