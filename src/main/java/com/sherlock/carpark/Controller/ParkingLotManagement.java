package com.sherlock.carpark.Controller;

import com.sherlock.carpark.Entity.ParkingLot;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CarPark/ParkingLotManager")
public class ParkingLotManagement {

    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping("/add")
    @ResponseBody
    ResponseEntity<ResponseObject> addParkingLot(@RequestBody ParkingLot newParkingLot) {
        return parkingLotService.saveParkingLot(newParkingLot);
    }

    @GetMapping("/view")
    ResponseEntity<ResponseObject> parkingLotList() {
        return parkingLotService.findParkingLotList();
    }
}
