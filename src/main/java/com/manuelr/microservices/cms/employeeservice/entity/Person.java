package com.manuelr.microservices.cms.employeeservice.entity;

import com.manuelr.microservices.cms.employeeservice.enums.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

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

//    @ElementCollection
//    @MapKeyEnumerated(EnumType.STRING)
//    private Map<PhoneType, String> phoneNumber;

    @Embedded
    private Address address;

    @NotNull
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Gender gender;

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", birthday=" + birthday +
                ", gender=" + gender +
                '}';
    }
}
