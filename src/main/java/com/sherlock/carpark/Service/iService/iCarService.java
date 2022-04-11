package com.sherlock.carpark.Service.iService;

import com.sherlock.carpark.DTO.RequestDTO.CarRequestDTO;
import com.sherlock.carpark.DTO.ResponseDTO.CarResponseDTO;
import com.sherlock.carpark.Entity.Car;
import com.sherlock.carpark.Entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface iCarService {
    public abstract ResponseEntity<ResponseObject> viewCarList(int pageNo, String sortBy, int maxElementPerPage);
    public abstract ResponseEntity<ResponseObject> saveCar(CarRequestDTO newCarDTO);
    public abstract ResponseEntity<ResponseObject> assignParkingLotForCar(int parkId, String licensePlate);
    public abstract Car findCarByLicensePlate(String licensePlate);
    public abstract Car convertToEntity(CarRequestDTO carRequestDTO);
    public abstract CarResponseDTO convertToDTO (Car car);
}
