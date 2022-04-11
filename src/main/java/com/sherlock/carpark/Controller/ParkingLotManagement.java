package com.sherlock.carpark.Controller;

import com.sherlock.carpark.DTO.RequestDTO.ParkingLotRequestDTO;
import com.sherlock.carpark.Entity.ParkingLot;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Service.iService.iParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/CarPark/ParkingLotManager")
public class ParkingLotManagement {

    private final iParkingLotService parkingLotService;

    @Autowired
    public ParkingLotManagement(iParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping("/add")
    @ResponseBody
    ResponseEntity<ResponseObject> addParkingLot(@Valid @RequestBody ParkingLotRequestDTO newParkingLotDTO) {
        return parkingLotService.saveParkingLot(newParkingLotDTO);
    }

    @GetMapping("/view")
    ResponseEntity<ResponseObject> parkingLotList() {
        return parkingLotService.viewParkingLotList();
    }
}
