package com.manuelr.microservices.cms.employeeservice.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Employee {
    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "employee_sequence",
            strategy = GenerationType.SEQUENCE
    )
    @Column(updatable = false, nullable = false)
    private Long id;

    @Version
    private Long version;

    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false)
    private String name;

    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false)
    private String lastName;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String phoneNumber;

    @NotNull
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate birthday;

    @Email
    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Position position;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee that = (Employee) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
