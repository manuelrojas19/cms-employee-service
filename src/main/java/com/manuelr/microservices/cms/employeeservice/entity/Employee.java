package com.manuelr.microservices.cms.employeeservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@PrimaryKeyJoinColumn(name = "person_id")
public class Employee extends Person {
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeType type;
    @ManyToOne
    private Manager manager;

}
