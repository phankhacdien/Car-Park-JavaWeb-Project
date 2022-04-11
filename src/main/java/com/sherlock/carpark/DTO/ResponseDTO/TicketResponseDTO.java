package com.sherlock.carpark.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponseDTO {

    private int ticketId;
    private String bookingTime;
    private String customerName;
}
