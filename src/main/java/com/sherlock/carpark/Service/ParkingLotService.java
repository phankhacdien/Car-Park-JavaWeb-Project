package com.sherlock.carpark.Service;

import com.sherlock.carpark.Entity.ParkingLot;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotService {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    public ResponseEntity<ResponseObject> findParkingLotList() {
        List<ParkingLot> parkingLotList = parkingLotRepository.findAll();
        if(!parkingLotList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Query All Parking Lots successfully", parkingLotList)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find any Lots to park", "")
            );
        }
    }

    public ResponseEntity<ResponseObject> saveParkingLot(ParkingLot newParkingLot) {
        parkingLotRepository.save(newParkingLot);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Add Car to Database Successfully", newParkingLot)
        );
    }

    public ResponseEntity<ResponseObject> findParkingLotById(int parkId) {
        Optional<ParkingLot> foundParkingLot = parkingLotRepository.findById(parkId);
        if(foundParkingLot.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Query Parking Lot successfully", foundParkingLot)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find Parking Lot with parkId = "+parkId, "")
            );
        }
    }
}
