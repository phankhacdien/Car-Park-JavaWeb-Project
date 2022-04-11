package com.sherlock.carpark.DTO.RequestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingOfficeRequestDTO {
    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "This field shouldn't be blanked! and formatted by: yyyy/MM/dd")
    private LocalDate startContractDeadline;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "This field shouldn't be blanked! and formatted by: yyyy/MM/dd")
    private LocalDate endContractDeadline;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String officePlace;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String officeName;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String officePhone;

    @NotNull(message = "This field shouldn't be blanked!")
    private int officePrice;
}
