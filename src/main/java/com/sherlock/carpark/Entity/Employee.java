package com.sherlock.carpark.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "tbl_Employee",
        uniqueConstraints = {
                @UniqueConstraint(name = "account_unique", columnNames = "account"),
                @UniqueConstraint(name = "Employee_Email_unique", columnNames = "Employee_Email"),
                @UniqueConstraint(name = "Employee_Phone_unique", columnNames = "Employee_Phone"),
    }
)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @NotEmpty(message = "Account must have value")
    @Column(name = "Account", nullable = false)
    private String account;

    @NotEmpty(message = "Department must have value")
    @Column(name = "Department", nullable = false)
    private String department;

    private String employeeAddress;
    private LocalDate employeeBirthDay;

    @Email
    @Column(name = "Employee_Email", nullable = true)
    private String employeeEmail;

    @NotEmpty(message = "Name must have value")
    @Column(name = "Employee_Name", nullable = false)
    private String employeeName;

    @NotEmpty(message = "Phone must have value")
    @Column(name = "Employee_Phone", nullable = false)
    private String employeePhone;

    @NotEmpty(message = "Password must have value")
    @Column(name = "Password", nullable = false)
    private String password;

    @NotEmpty(message = "Genre must have value")
    @Column(name = "Genre", nullable = false)
    private String genre;
}
