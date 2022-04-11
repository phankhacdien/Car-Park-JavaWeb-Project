package com.sherlock.carpark.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripResponseDTO {
    private int tripId;
    private int bookedTicketNumber;
    private String carType;
    private LocalTime departureTime;
    private String destination;
    private String driver;
}
