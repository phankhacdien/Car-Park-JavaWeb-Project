package com.sherlock.carpark.Service;

import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Entity.Trip;
import com.sherlock.carpark.Repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    public ResponseEntity<ResponseObject> findTripList() {
        List<Trip> tripList = tripRepository.findAll();
        if(!tripList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Query All Trips successfully", tripList)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find any Trips", "")
            );
        }
    }

    public ResponseEntity<ResponseObject> saveTrip(Trip newTrip) {
        tripRepository.save(newTrip);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Add Trip to Database Successfully", newTrip)
        );
    }

    public ResponseEntity<ResponseObject> findTripById(int tripId) {
        Optional<Trip> foundTrip = tripRepository.findById(tripId);
        if(foundTrip.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Query Trip successfully", foundTrip)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find trip with tripId = "+tripId, "")
            );
        }
    }
}
