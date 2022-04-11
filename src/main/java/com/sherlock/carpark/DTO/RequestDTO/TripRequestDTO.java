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
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripRequestDTO {

    @NotNull(message = "This field shouldn't be blanked!")
    private int bookedTicketNumber;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String carType;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "This field shouldn't be blanked! and formatted by: yyyy/MM/dd")
    private LocalDate departureDate;

    @JsonFormat(pattern = "HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull(message = "This field shouldn't be blanked! and formatted by: HH:mm:ss")
    private LocalTime departureTime;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String destination;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String driver;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String maximumOnlineTicketNumber;
}
