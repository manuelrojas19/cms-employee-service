package com.manuelr.microservices.cms.employeeservice.dto;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Getter
@Setter
public abstract class PersonDto extends RepresentationModel<PersonDto> {
    @JsonIgnore
    private static final long serialVersionUID = -3206250214216783587L;
    @Null
    private Long id;
    @Null
    private Long version;
    @NotBlank
    private String firstName;
}
