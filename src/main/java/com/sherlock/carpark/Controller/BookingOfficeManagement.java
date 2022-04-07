package com.sherlock.carpark.Controller;

import com.sherlock.carpark.Entity.BookingOffice;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/CarPark/BookingOfficeManager")
public class BookingOfficeManagement {

    @Autowired
    BookingService bookingService;

    @PostMapping("/add")
    @ResponseBody
    ResponseEntity<ResponseObject> addBookingOffice(@RequestBody BookingOffice newBookingOffice) {
        return bookingService.saveBookingOffice(newBookingOffice);
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
