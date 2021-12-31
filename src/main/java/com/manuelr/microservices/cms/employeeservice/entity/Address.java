package com.manuelr.microservices.cms.employeeservice.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Address implements Serializable {
    private String street;
    private String number;
    private String settlement;
    private String postalCode;
    private String locality;
    private String municipality;
    private String state;
    private String country;
}
