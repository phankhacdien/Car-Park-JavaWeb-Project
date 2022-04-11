package com.sherlock.carpark.DTO.RequestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDTO {
    private enum genre {
        FEMALE, MALE
    }

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String account;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String department;

    private String employeeAddress;

    @Email
    private String employeeEmail;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "This field shouldn't be blanked! and formatted by: yyyy/MM/dd")
    private LocalDate employeeBirthDay;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String employeeName;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String employeePhone;

    @NotEmpty(message = "This field shouldn't be blanked!")
    @Min(value = 6, message = "The password must have at least 6 characters, including uppercase, lowercase and number")
    private String password;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String genre;
}
