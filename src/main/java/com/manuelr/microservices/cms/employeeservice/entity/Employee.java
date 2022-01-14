package com.manuelr.microservices.cms.employeeservice.entity;

import com.manuelr.microservices.cms.employeeservice.enums.EmployeeType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "person_id")
public class Employee extends Person {
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    private String position;

    @ManyToOne
    private Manager manager;
}
