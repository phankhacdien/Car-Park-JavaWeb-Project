package com.sherlock.carpark.Service.iService;

import com.sherlock.carpark.DTO.RequestDTO.TicketRequestDTO;
import com.sherlock.carpark.DTO.ResponseDTO.TicketResponseDTO;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Entity.Ticket;
import org.springframework.http.ResponseEntity;

public interface iTicketService {
    public abstract ResponseEntity<ResponseObject> saveTicket(TicketRequestDTO newTicketDTO);
    public abstract ResponseEntity<ResponseObject> viewAllTicket();
    public abstract ResponseEntity<ResponseObject> deleteTicketById(int ticketId);
    public abstract Ticket findTicketById(int ticketId);
    public abstract ResponseEntity<ResponseObject> assignCarForTicket(String licensePlate, int ticketId);
    public abstract ResponseEntity<ResponseObject> assignTripForTicket(int tripId, int ticketId);
    public abstract Ticket convertToEntity(TicketRequestDTO ticketRequestDTO);
    public abstract TicketResponseDTO convertToDTO (Ticket ticket);
}
