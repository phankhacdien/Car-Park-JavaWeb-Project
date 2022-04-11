package com.sherlock.carpark.Controller;

import com.sherlock.carpark.DTO.RequestDTO.TicketRequestDTO;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Entity.Ticket;
import com.sherlock.carpark.Service.iService.iTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/CarPark/TicketManager")
public class TicketManagement {

    private final iTicketService ticketService;

    @Autowired
    public TicketManagement(iTicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/view")
    ResponseEntity<ResponseObject> ticketList() {
        return ticketService.viewAllTicket();
    }

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addTicket(@Valid @RequestBody TicketRequestDTO newTicketDTO) {
        return ticketService.saveTicket(newTicketDTO);
    }

    @DeleteMapping("/delete/{ticketId}")
    ResponseEntity<ResponseObject> deleteTicketById(@PathVariable int ticketId) {
        return ticketService.deleteTicketById(ticketId);
    }

    @PutMapping("/selectCar/{licensePlate}/for/{ticketId}")
    ResponseEntity<ResponseObject> selectCarForTicket(@PathVariable String licensePlate, @PathVariable int ticketId) {
        return ticketService.assignCarForTicket(licensePlate, ticketId);
    }

    @PutMapping("/selectTrip/{tripId}/for/{ticketId}")
    ResponseEntity<ResponseObject> selectCarForTicket(@PathVariable int tripId, @PathVariable int ticketId) {
        return ticketService.assignTripForTicket(tripId, ticketId);
    }
}
