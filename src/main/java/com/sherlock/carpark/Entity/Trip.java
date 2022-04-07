package com.sherlock.carpark.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_Trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripId;
    private int bookedTicketNumber;

    @Column(nullable = false)
    private String carType;

    @Column(nullable = true)
    private LocalDate departureDate;

    @Column(nullable = true)
    private LocalTime departureTime;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private String driver;

    @Column(nullable = false)
    private String maximumOnlineTicketNumber;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trip", cascade = CascadeType.ALL)
    private Set<Ticket> ticketSet;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trip", cascade = CascadeType.ALL)
    private Set<BookingOffice> bookingOfficeSet;
}
