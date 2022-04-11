package com.sherlock.carpark.Service.ServiceImp;

import com.sherlock.carpark.DTO.RequestDTO.BookingOfficeRequestDTO;
import com.sherlock.carpark.DTO.ResponseDTO.BookingOfficeResponseDTO;
import com.sherlock.carpark.Entity.BookingOffice;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Entity.Trip;
import com.sherlock.carpark.Repository.BookingOfficeRepository;
import com.sherlock.carpark.Service.iService.iBookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService implements iBookingService {

    private final BookingOfficeRepository bookingOfficeRepository;
    private final TripService tripService;
    private final ModelMapper mapper;

    @Autowired
    public BookingService(BookingOfficeRepository bookingOfficeRepository, TripService tripService, ModelMapper mapper) {
        this.bookingOfficeRepository = bookingOfficeRepository;
        this.tripService = tripService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ResponseObject> saveBookingOffice(BookingOfficeRequestDTO newBookingOfficeDTO) {
        BookingOffice newBookingOffice = convertToEntity(newBookingOfficeDTO);
        bookingOfficeRepository.save(newBookingOffice);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Add Booking Office to Database Successfully", newBookingOfficeDTO)
        );
    }

    @Override
    public ResponseEntity<ResponseObject> findAllBooking(int pageNo, String sortBy, int maxElementPerPage) {
        List<BookingOfficeResponseDTO> bookingOfficeList = bookingOfficeRepository
                .findAll(PageRequest.of(pageNo, maxElementPerPage, Sort.Direction.ASC, sortBy)).getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

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

    @Override
    public ResponseEntity<ResponseObject> assignTripForBookingOffice(int tripId, int officeId) {
        Trip foundTrip = tripService.findTripById(tripId);
        BookingOffice assignedBookingOffice = bookingOfficeRepository.findById(officeId).orElse(null);
        if (foundTrip == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not Found", "Cannot find any trips with Id = "+tripId, "")
            );
        }else if(assignedBookingOffice == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not found", "Cannot find any Booking Offices", "")
            );
        }else {
            assignedBookingOffice.setTrip(foundTrip);
            bookingOfficeRepository.save(assignedBookingOffice);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Assign trip for Booking Office Successfully", convertToDTO(assignedBookingOffice))
            );
        }
    }

    @Override
    public BookingOffice convertToEntity(BookingOfficeRequestDTO bookingOfficeRequestDTO) {
        return mapper.map(bookingOfficeRequestDTO, BookingOffice.class);
    }

    @Override
    public BookingOfficeResponseDTO convertToDTO (BookingOffice bookingOffice) {
        return mapper.map(bookingOffice, BookingOfficeResponseDTO.class);
    }
}