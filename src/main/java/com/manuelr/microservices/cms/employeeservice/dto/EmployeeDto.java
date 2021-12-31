package com.manuelr.microservices.cms.employeeservice.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.manuelr.microservices.cms.employeeservice.entity.Gender;
import com.manuelr.microservices.cms.employeeservice.entity.EmployeeType;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Relation(collectionRelation = "employees")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class EmployeeDto extends RepresentationModel<EmployeeDto> implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = -3206250214216783587L;

    @Null
    private Long id;
    @Null
    private Long version;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phoneNumber;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd", shape=JsonFormat.Shape.STRING)
    private LocalDate birthday;
    @NotBlank
    @Email
    private String email;
    @NotNull
    private Gender gender;
    @NotNull
    private EmployeeType position;
    @Null
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime createdAt;
    @Null
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime updatedAt;
    @Null
    private CollectionModel<CommissionDto> commissions;
}
