package com.sherlock.carpark.Service;

import com.sherlock.carpark.Repository.CarRepository;
import com.sherlock.carpark.Entity.Car;
import com.sherlock.carpark.Entity.ParkingLot;
import com.sherlock.carpark.Entity.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    ParkingLotService parkingLotService;

    public ResponseEntity<ResponseObject> findCarList() {
        List<Car> carList = carRepository.findAll();
        if(!carList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Query All Cars successfully", carList)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find any Cars", "")
            );
        }
    }

    public ResponseEntity<ResponseObject> saveCar(Car newCar) {
        carRepository.save(newCar);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Add Car to Database Successfully", newCar)
        );
    }

    public ResponseEntity<ResponseObject> assignParkingLotForCar(int parkId, String licensePlate) {
        Optional<ParkingLot> parkingLotOptional= (Optional<ParkingLot>) parkingLotService.findParkingLotById(parkId).getBody().getData();
        ParkingLot foundparkingLot = parkingLotOptional.get();
        Car assignedCar = carRepository.findById(licensePlate).get();
        assignedCar.setParkingLot(foundparkingLot);
        carRepository.save(assignedCar);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Assign Parking Lot for Car Successfully", assignedCar)
        );
    }

    public ResponseEntity<ResponseObject> findCarByLicensePlate(String licensePlate) {
        Optional<Car> foundCar = carRepository.findById(licensePlate);
        if(foundCar.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Query Car successfully", foundCar)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find Car with license plate = "+licensePlate, "")
            );
        }
    }
}
