package com.sherlock.carpark.Service.iService;

import com.sherlock.carpark.DTO.RequestDTO.BookingOfficeRequestDTO;
import com.sherlock.carpark.DTO.ResponseDTO.BookingOfficeResponseDTO;
import com.sherlock.carpark.Entity.BookingOffice;
import com.sherlock.carpark.Entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface iBookingService {
    public abstract ResponseEntity<ResponseObject> saveBookingOffice(BookingOfficeRequestDTO newBookingOfficeDTO);
    public abstract ResponseEntity<ResponseObject> findAllBooking();
    public abstract ResponseEntity<ResponseObject> assignTripForBookingOffice(int tripId, int officeId);
    public abstract BookingOffice convertToEntity(BookingOfficeRequestDTO bookingOfficeRequestDTO);
    public abstract BookingOfficeResponseDTO convertToDTO (BookingOffice bookingOffice);
}
