package com.sherlock.carpark.Service.ServiceImp;

import com.sherlock.carpark.DTO.RequestDTO.TripRequestDTO;
import com.sherlock.carpark.DTO.ResponseDTO.TripResponseDTO;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Entity.Trip;
import com.sherlock.carpark.Repository.TripRepository;
import com.sherlock.carpark.Service.iService.iTripService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripService implements iTripService {

    private final TripRepository tripRepository;
    private final ModelMapper mapper;

    @Autowired
    public TripService(TripRepository tripRepository, ModelMapper mapper) {
        this.tripRepository = tripRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ResponseObject> findTripList(int pageNo, String sortBy, int maxElementPerPage) {
        List<TripResponseDTO> tripList = tripRepository
                .findAll(PageRequest.of(pageNo, maxElementPerPage, Sort.Direction.ASC, sortBy)).getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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

    @Override
    public ResponseEntity<ResponseObject> saveTrip(TripRequestDTO newTripDTO) {
        tripRepository.save(convertToEntity(newTripDTO));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Add Trip to Database Successfully", newTripDTO)
        );
    }

    @Override
    public Trip findTripById(int tripId) {
        return tripRepository.findById(tripId).orElse(null);
    }

    public ResponseEntity<ResponseObject> viewTripById(int tripId) {
        Trip foundTrip = findTripById(tripId);
        if (foundTrip != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Query trip successfully", convertToDTO(foundTrip))
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not Found", "Cannot find any trips with Id = "+tripId, "")
            );
        }
    }

    @Override
    public Trip convertToEntity(TripRequestDTO tripRequestDTO) {
        return mapper.map(tripRequestDTO, Trip.class);
    }

    @Override
    public TripResponseDTO convertToDTO (Trip trip) {
        return mapper.map(trip, TripResponseDTO.class);
    }
}
