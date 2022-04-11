package com.sherlock.carpark.Service.ServiceImp;

import com.sherlock.carpark.DTO.RequestDTO.ParkingLotRequestDTO;
import com.sherlock.carpark.DTO.ResponseDTO.ParkingLotResponseDTO;
import com.sherlock.carpark.Entity.ParkingLot;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Repository.ParkingLotRepository;
import com.sherlock.carpark.Service.iService.iParkingLotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingLotService implements iParkingLotService {

    private final ParkingLotRepository parkingLotRepository;
    private final ModelMapper mapper;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository, ModelMapper mapper) {
        this.parkingLotRepository = parkingLotRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ResponseObject> viewParkingLotList() {
        List<ParkingLotResponseDTO> parkingLotList = parkingLotRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
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

    @Override
    public ResponseEntity<ResponseObject> saveParkingLot(ParkingLotRequestDTO newParkingLotDTO) {
        parkingLotRepository.save(convertToEntity(newParkingLotDTO));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Add Car to Database Successfully", newParkingLotDTO)
        );
    }

    @Override
    public ResponseEntity<ResponseObject> viewParkingLotById(int parkId) {
        ParkingLot foundParkingLot = findParkingLotById(parkId);
        if(foundParkingLot == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find Parking Lot with parkId = "+parkId, "")
            );
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Query Parking Lot successfully", convertToDTO(foundParkingLot))
            );
        }
    }

    @Override
    public ParkingLot findParkingLotById(int parkId) {
        return parkingLotRepository.findById(parkId).orElse(null);
    }

    @Override
    public ParkingLot convertToEntity(ParkingLotRequestDTO parkingLotRequestDTO) {
        return mapper.map(parkingLotRequestDTO, ParkingLot.class);
    }

    @Override
    public ParkingLotResponseDTO convertToDTO (ParkingLot parkingLot) {
        return mapper.map(parkingLot, ParkingLotResponseDTO.class);
    }
}
