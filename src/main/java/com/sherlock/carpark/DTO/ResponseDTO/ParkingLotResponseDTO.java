package com.sherlock.carpark.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingLotResponseDTO {

    private int parkId;
    private int parkArea;
    private String parkName;
    private String parkPlace;
    private int parkPrice;
    private String parkStatus;
}
