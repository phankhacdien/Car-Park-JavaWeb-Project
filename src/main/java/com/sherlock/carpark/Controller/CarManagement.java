package com.sherlock.carpark.Controller;

import com.sherlock.carpark.DTO.RequestDTO.CarRequestDTO;
import com.sherlock.carpark.Entity.Car;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Service.iService.iCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CarPark/CarManager")
public class CarManagement {

    iCarService carService;

    public CarManagement(iCarService carService) {
        this.carService = carService;
    }

    @GetMapping("/view")
    ResponseEntity<ResponseObject> viewCarList() {
        return carService.viewCarList();
    }

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addCar(@RequestBody CarRequestDTO newCarDTO) {
        return carService.saveCar(newCarDTO);
    }

    @PutMapping("/selectParkingLot/{parkId}/for/{licensePlate}")
    ResponseEntity<ResponseObject> selectParkingLotForCar(@PathVariable int parkId, @PathVariable String licensePlate) {
        return carService.assignParkingLotForCar(parkId, licensePlate);
    }
}
