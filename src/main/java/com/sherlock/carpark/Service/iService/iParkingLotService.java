package com.sherlock.carpark.Service.iService;

import com.sherlock.carpark.DTO.RequestDTO.ParkingLotRequestDTO;
import com.sherlock.carpark.DTO.ResponseDTO.ParkingLotResponseDTO;
import com.sherlock.carpark.Entity.ParkingLot;
import com.sherlock.carpark.Entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface iParkingLotService {
    public abstract ResponseEntity<ResponseObject> viewParkingLotList(int pageNo, String sortBy, int maxElementPerPage);
    public abstract ResponseEntity<ResponseObject> saveParkingLot(ParkingLotRequestDTO newParkingLotDTO);
    public abstract ResponseEntity<ResponseObject> viewParkingLotById(int parkId);
    public abstract ParkingLot findParkingLotById(int parkId);
    public abstract ParkingLot convertToEntity(ParkingLotRequestDTO parkingLotRequestDTO);
    public abstract ParkingLotResponseDTO convertToDTO (ParkingLot parkingLot);
}
