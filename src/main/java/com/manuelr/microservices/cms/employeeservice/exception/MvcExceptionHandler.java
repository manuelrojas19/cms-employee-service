package com.manuelr.microservices.cms.employeeservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class MvcExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationErrorHandler(MethodArgumentNotValidException e) {
        Map<String, String> errorList = e.getFieldErrors()
                .stream().collect(Collectors
                        .toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
        log.info("Sending validationError response to the client");
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ExceptionResponse> runtimeExceptionHandler(RuntimeException e) {
//        ExceptionResponse response = ExceptionResponse.builder().message(e.getMessage()).build();
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> employeeNotFoundHandler(NotFoundException e) {
        ExceptionResponse response = ExceptionResponse.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
