package com.manuelr.microservices.cms.employeeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person extends BaseEntity implements Serializable {

    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false)
    private String firstName;

    @Column(columnDefinition = "TEXT")
    private String secondName;

    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false)
    private String firstSurname;

    @Column(columnDefinition = "TEXT")
    private String secondSurname;

    @NotBlank
    @Email
    @Column(columnDefinition = "TEXT", nullable = false, unique = true)
    private String email;

    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    private Map<PhoneType, String> phoneNumber;

    @Embedded
    private Address address;

    @NotNull
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Gender gender;

}
