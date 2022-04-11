package com.sherlock.carpark.Service.ServiceImp;

import com.sherlock.carpark.DTO.RequestDTO.BookingOfficeRequestDTO;
import com.sherlock.carpark.DTO.RequestDTO.TicketRequestDTO;
import com.sherlock.carpark.DTO.ResponseDTO.BookingOfficeResponseDTO;
import com.sherlock.carpark.DTO.ResponseDTO.TicketResponseDTO;
import com.sherlock.carpark.Entity.*;
import com.sherlock.carpark.Repository.TicketRepository;
import com.sherlock.carpark.Service.iService.iTicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService implements iTicketService {

    private final TicketRepository ticketRepository;
    private final CarService carService;
    private final TripService tripService;
    private final ModelMapper mapper;

    @Autowired
    public TicketService(TicketRepository ticketRepository, CarService carService, TripService tripService, ModelMapper mapper) {
        this.ticketRepository = ticketRepository;
        this.carService = carService;
        this.tripService = tripService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ResponseObject> saveTicket(TicketRequestDTO newTicketDTO) {
        ticketRepository.save(convertToEntity(newTicketDTO));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Add Ticket to Database Successfully", newTicketDTO)
        );
    }

    @Override
    public ResponseEntity<ResponseObject> viewAllTicket() {
        List<TicketResponseDTO> ticketList = ticketRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
        if(!ticketList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Query All Tickets successfully", ticketList)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find any Tickets", "")
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> deleteTicketById(int ticketId) {
        Ticket foundTicket = findTicketById(ticketId);
        if (foundTicket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find Ticket with ticketId = "+ticketId, "")
            );
        }else {
            ticketRepository.deleteById(ticketId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Delete Ticket Successfully", convertToDTO(foundTicket))
            );
        }
    }

    @Override
    public Ticket findTicketById(int ticketId) {
        return ticketRepository.findById(ticketId).orElse(null);
    }

    @Override
    public ResponseEntity<ResponseObject> assignCarForTicket(String licensePlate, int ticketId) {
        Car foundCar = carService.findCarByLicensePlate(licensePlate);
        Ticket assignedTicket = ticketRepository.findById(ticketId).orElse(null);

        if (foundCar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find Car with license plate = "+licensePlate, "")
            );
        } else if (assignedTicket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find Ticket with ticketId = "+ticketId, "")
            );
        } else {
            assignedTicket.setCar(foundCar);
            ticketRepository.save(assignedTicket);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Assign Parking Lot for Car Successfully", convertToDTO(assignedTicket))
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> assignTripForTicket(int tripId, int ticketId) {
        Trip foundTrip = tripService.findTripById(tripId);
        Ticket assignedTicket = ticketRepository.findById(ticketId).orElse(null);

        if (foundTrip == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find trip with trip Id = "+tripId, "")
            );
        }else if (assignedTicket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find Ticket with ticketId = "+ticketId, "")
            );
        }else {
            assignedTicket.setTrip(foundTrip);
            ticketRepository.save(assignedTicket);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Assign Parking Lot for Car Successfully", convertToDTO(assignedTicket))
            );
        }
    }

    @Override
    public Ticket convertToEntity(TicketRequestDTO ticketRequestDTO) {
        return mapper.map(ticketRequestDTO, Ticket.class);
    }

    @Override
    public TicketResponseDTO convertToDTO (Ticket ticket) {
        return mapper.map(ticket, TicketResponseDTO.class);
    }
}
