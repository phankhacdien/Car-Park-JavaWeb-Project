package com.sherlock.carpark.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO {
    private int employeeId;
    private String department;
    private String employeeAddress;
    private String employeeBirthDay;
    private String employeeName;
    private String employeePhone;
}
