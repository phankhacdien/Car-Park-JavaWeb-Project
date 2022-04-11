package com.sherlock.carpark.Service.iService;

import com.sherlock.carpark.DTO.RequestDTO.TripRequestDTO;
import com.sherlock.carpark.DTO.ResponseDTO.TripResponseDTO;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Entity.Trip;
import org.springframework.http.ResponseEntity;

public interface iTripService {
    public abstract ResponseEntity<ResponseObject> findTripList(int pageNo, String sortBy, int maxElementPerPage);
    public abstract ResponseEntity<ResponseObject> saveTrip(TripRequestDTO newTripDTO);
    public abstract ResponseEntity<ResponseObject> viewTripById(int tripId);
    public abstract Trip findTripById(int tripId);
    public abstract Trip convertToEntity(TripRequestDTO tripRequestDTO);
    public abstract TripResponseDTO convertToDTO (Trip trip);
}
