package com.sherlock.carpark.Service;

import com.sherlock.carpark.Entity.Car;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Entity.Ticket;
import com.sherlock.carpark.Entity.Trip;
import com.sherlock.carpark.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    CarService carService;

    @Autowired
    TripService tripService;

    public ResponseEntity<ResponseObject> saveTicket(Ticket newTicket) {
        ticketRepository.save(newTicket);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Add Ticket to Database Successfully", newTicket)
        );
    }

    public ResponseEntity<ResponseObject> findAllTicket() {
        List<Ticket> ticketList = ticketRepository.findAll();
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

    public ResponseEntity<ResponseObject> deleteTicketById(int ticketId) {
        Optional<Ticket> ticketOptional = (Optional<Ticket>) findTicketById(ticketId).getBody().getData();
        Ticket foundTicket = ticketOptional.get();
        ticketRepository.deleteById(ticketId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Delete Ticket Successfully", foundTicket)
        );
    }

    public ResponseEntity<ResponseObject> findTicketById(int ticketId) {
        Optional<Ticket> foundTicket = ticketRepository.findById(ticketId);
        if(foundTicket.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Query Trip successfully", foundTicket)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find Ticket with ticketId = "+ticketId, "")
            );
        }
    }

    public ResponseEntity<ResponseObject> assignCarForTicket(String licensePlate, int ticketId) {
        Optional<Car> carOptional= (Optional<Car>) carService.findCarByLicensePlate(licensePlate).getBody().getData();
        Car foundCar = carOptional.get();
        Ticket assignedTicket = ticketRepository.findById(ticketId).get();
        assignedTicket.setCar(foundCar);
        ticketRepository.save(assignedTicket);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Assign Parking Lot for Car Successfully", assignedTicket)
        );
    }

    public ResponseEntity<ResponseObject> assignTripForTicket(int tripId, int ticketId) {
        Optional<Trip> tripOptional= (Optional<Trip>) tripService.findTripById(tripId).getBody().getData();
        Trip foundTrip = tripOptional.get();
        Ticket assignedTicket = ticketRepository.findById(ticketId).get();
        assignedTicket.setTrip(foundTrip);
        ticketRepository.save(assignedTicket);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Assign Parking Lot for Car Successfully", assignedTicket)
        );
    }
}
