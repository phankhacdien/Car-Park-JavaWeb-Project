package com.sherlock.carpark.Controller;

import com.sherlock.carpark.Entity.Car;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CarPark/CarManager")
public class CarManagement {

    @Autowired
    CarService carService;

    @GetMapping("/view")
    ResponseEntity<ResponseObject> viewCarList() {
        return carService.findCarList();
    }

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addCar(@RequestBody Car newCar) {
        return carService.saveCar(newCar);
    }

    @PutMapping("/selectParkingLot/{parkId}/for/{licensePlate}")
    ResponseEntity<ResponseObject> selectParkingLotForCar(@PathVariable int parkId, @PathVariable String licensePlate) {
        return carService.assignParkingLotForCar(parkId, licensePlate);
    }
}
