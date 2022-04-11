package com.sherlock.carpark.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarRequestDTO {

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String licensePlate;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String carColor;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String carType;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String company;
}

