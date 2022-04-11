package com.sherlock.carpark.Service.ServiceImp;

import com.sherlock.carpark.DTO.RequestDTO.CarRequestDTO;
import com.sherlock.carpark.DTO.RequestDTO.ParkingLotRequestDTO;
import com.sherlock.carpark.DTO.ResponseDTO.CarResponseDTO;
import com.sherlock.carpark.DTO.ResponseDTO.ParkingLotResponseDTO;
import com.sherlock.carpark.Repository.CarRepository;
import com.sherlock.carpark.Entity.Car;
import com.sherlock.carpark.Entity.ParkingLot;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Service.iService.iCarService;
import com.sherlock.carpark.Service.iService.iParkingLotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService implements iCarService {

    private final CarRepository carRepository;
    private final iParkingLotService parkingLotService;
    private final ModelMapper mapper;

    @Autowired
    public CarService(CarRepository carRepository, iParkingLotService parkingLotService, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.parkingLotService = parkingLotService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ResponseObject> viewCarList(int pageNo, String sortBy, int maxElementPerPage) {
        List<CarResponseDTO> carList = carRepository
                .findAll(PageRequest.of(pageNo, maxElementPerPage, Sort.Direction.ASC,sortBy)).getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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

    @Override
    public ResponseEntity<ResponseObject> saveCar(CarRequestDTO newCarDTO) {
        carRepository.save(convertToEntity(newCarDTO));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Add Car to Database Successfully", newCarDTO)
        );
    }

    @Override
    public ResponseEntity<ResponseObject> assignParkingLotForCar(int parkId, String licensePlate) {
        ParkingLot foundParkingLot =  parkingLotService.findParkingLotById(parkId);
        Car assignedCar = carRepository.findById(licensePlate).orElse(null);
        if (foundParkingLot == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find Parking Lot with parkID = "+parkId, "")
            );
        } else if (assignedCar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find Car with license plate = "+licensePlate, "")
            );
        } else {
            assignedCar.setParkingLot(foundParkingLot);
            carRepository.save(assignedCar);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Assign Parking Lot for Car Successfully", convertToDTO(assignedCar))
            );
        }
    }

    @Override
    public Car findCarByLicensePlate(String licensePlate) {
        return carRepository.findById(licensePlate).orElse(null);
    }

    @Override
    public Car convertToEntity(CarRequestDTO carRequestDTO) {
        return mapper.map(carRequestDTO, Car.class);
    }

    @Override
    public CarResponseDTO convertToDTO (Car car) {
        return mapper.map(car, CarResponseDTO.class);
    }
}
