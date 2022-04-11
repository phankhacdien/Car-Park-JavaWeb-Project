package com.sherlock.carpark.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingLotRequestDTO {

    @NotNull(message = "This field shouldn't be blanked!")
    private int parkArea;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String parkName;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String parkPlace;

    @NotNull(message = "This field shouldn't be blanked!")
    private int parkPrice;

    @NotEmpty(message = "This field shouldn't be blanked!")
    private String parkStatus;
}
