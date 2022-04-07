package com.sherlock.carpark.Service;

import com.sherlock.carpark.Entity.BookingOffice;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Entity.Trip;
import com.sherlock.carpark.Repository.BookingOfficeRepository;
import com.sherlock.carpark.Repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingOfficeRepository bookingOfficeRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    TripService tripService;

    public ResponseEntity<ResponseObject> saveBookingOffice(BookingOffice newBookingOffice) {
        bookingOfficeRepository.save(newBookingOffice);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Add Booking Office to Database Successfully", newBookingOffice)
        );
    }

    public ResponseEntity<ResponseObject> findAllBooking() {
        List<BookingOffice> bookingOfficeList = bookingOfficeRepository.findAll();
        if(!bookingOfficeList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Query All Booking Offices successfully", bookingOfficeList)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find any Booking Offices", "")
            );
        }
    }

    public ResponseEntity<ResponseObject> assignTripForBookingOffice(int tripId, int officeId) {
        Optional<Trip> tripOptional= (Optional<Trip>) tripService.findTripById(tripId).getBody().getData();
        Trip foundTrip = tripOptional.get();
        BookingOffice assignedBookingOffice = bookingOfficeRepository.findById(officeId).get();
        assignedBookingOffice.setTrip(foundTrip);
        bookingOfficeRepository.save(assignedBookingOffice);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Assign trip for Booking Office Successfully", assignedBookingOffice)
        );
    }
}