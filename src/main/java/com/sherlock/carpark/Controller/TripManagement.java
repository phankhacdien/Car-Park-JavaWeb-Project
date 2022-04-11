package com.sherlock.carpark.Controller;

import com.sherlock.carpark.DTO.RequestDTO.TripRequestDTO;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Service.iService.iTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/CarPark/TripManager")
public class TripManagement {

    private final iTripService tripService;

    @Autowired
    public TripManagement(iTripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/view")
    ResponseEntity<ResponseObject> viewTripList() {
        return tripService.findTripList();
    }

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addTrip(@Valid @RequestBody TripRequestDTO newTripDTO) {
        return tripService.saveTrip(newTripDTO);
    }

    @GetMapping("/view/{tripId}")
    ResponseEntity<ResponseObject> viewTripById(@PathVariable int tripId) {
        return tripService.viewTripById(tripId);
    }
}
