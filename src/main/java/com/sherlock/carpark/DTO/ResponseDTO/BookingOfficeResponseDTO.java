package com.sherlock.carpark.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingOfficeResponseDTO {
    private int officeId;
    private LocalDate startContractDeadline;
    private LocalDate endContractDeadline;
    private String officeName;
    private String officePhone;
//    private String destination;
}
