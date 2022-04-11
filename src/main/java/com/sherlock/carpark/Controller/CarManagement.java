package com.sherlock.carpark.Controller;

import com.sherlock.carpark.DTO.RequestDTO.CarRequestDTO;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Service.iService.iCarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/CarPark/CarManager")
public class CarManagement {

    iCarService carService;

    public CarManagement(iCarService carService) {
        this.carService = carService;
    }

    @GetMapping("/view")
    ResponseEntity<ResponseObject> viewCarList(@RequestParam Optional<Integer> pageNo,
                                               @RequestParam Optional<String> sortBy,
                                               @RequestParam Optional<Integer> maxElementPerPage) {
        return carService.viewCarList(pageNo.orElse(0), sortBy.orElse("licensePlate"), maxElementPerPage.orElse(5));
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
