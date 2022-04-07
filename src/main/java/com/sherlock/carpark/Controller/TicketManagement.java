package com.sherlock.carpark.Controller;

import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Entity.Ticket;
import com.sherlock.carpark.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CarPark/TicketManager")
public class TicketManagement {

    @Autowired
    TicketService ticketService;

    @GetMapping("/view")
    ResponseEntity<ResponseObject> ticketList() {
        return ticketService.findAllTicket();
    }

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addTicket(@RequestBody Ticket newTicket) {
        return ticketService.saveTicket(newTicket);
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
