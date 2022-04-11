package com.sherlock.carpark.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "Account", nullable = false)
    private String account;

    @Column(name = "Department", nullable = false)
    private String department;

    private String employeeAddress;
    private LocalDate employeeBirthDay;

    @Column(name = "Employee_Email", nullable = true)
    private String employeeEmail;

    @Column(name = "Employee_Name", nullable = false)
    private String employeeName;

    @Column(name = "Employee_Phone", nullable = false)
    private String employeePhone;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Genre", nullable = false)
    private String genre;
}
