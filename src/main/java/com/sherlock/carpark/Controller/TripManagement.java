package com.sherlock.carpark.Controller;

import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Entity.Trip;
import com.sherlock.carpark.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CarPark/TripManager")
public class TripManagement {

    @Autowired
    TripService tripService;

    @GetMapping("/view")
    ResponseEntity<ResponseObject> viewTripList() {
        return tripService.findTripList();
    }

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addTrip(@RequestBody Trip newTrip) {
        return tripService.saveTrip(newTrip);
    }

    @GetMapping("/view/{tripId}")
    ResponseEntity<ResponseObject> viewTripById(@PathVariable int tripId) {
        return tripService.findTripById(tripId);
    }
}
