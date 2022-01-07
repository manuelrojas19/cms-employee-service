package com.manuelr.microservices.cms.employeeservice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponseDto {
    String message;
}
