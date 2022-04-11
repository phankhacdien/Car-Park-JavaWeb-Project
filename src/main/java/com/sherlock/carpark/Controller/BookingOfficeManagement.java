package com.sherlock.carpark.Controller;

import com.sherlock.carpark.DTO.RequestDTO.BookingOfficeRequestDTO;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Service.iService.iBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/CarPark/BookingOfficeManager")
//"api/v1"
public class BookingOfficeManagement {

    private final iBookingService bookingService;

    @Autowired
    public BookingOfficeManagement(iBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/add")
    @ResponseBody
    ResponseEntity<ResponseObject> addBookingOffice(@Valid @RequestBody BookingOfficeRequestDTO newBookingOfficeDTO) {
        return bookingService.saveBookingOffice(newBookingOfficeDTO);
    }

    @GetMapping("/viewAllBookingList")
    ResponseEntity<ResponseObject> viewAllBooking() {
        return bookingService.findAllBooking();
    }

    @PutMapping("/selectTrip/{tripId}/for/{officeId}")
    ResponseEntity<ResponseObject> selectTripForBookingOffice(@PathVariable int tripId, @PathVariable int officeId) {
        return bookingService.assignTripForBookingOffice(tripId, officeId);
    }

}
