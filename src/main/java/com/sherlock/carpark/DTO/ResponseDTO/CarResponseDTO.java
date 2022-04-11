package com.sherlock.carpark.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarResponseDTO {
    private String licensePlate;
    private String carColor;
    private String carType;
    private String company;
}
