package com.manuelr.microservices.cms.employeeservice.dto;

import lombok.Builder;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@Builder
public class ManagerDto extends PersonDto {
    @JsonIgnore
    private static final long serialVersionUID = -3206250214216783587L;
    @Null
    private Long id;
    @Null
    private Long version;
    @NotBlank
    private String firstName;
}
