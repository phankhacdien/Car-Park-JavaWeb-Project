package com.sherlock.carpark.Entity;

import com.sherlock.carpark.Entity.Trip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_Booking_office")
public class BookingOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int officeId;

    private LocalDate startContractDeadline;
    private LocalDate endContractDeadline;
    private String officePlace;

    @Column(nullable = false)
    private String officeName;

    @Column(nullable = false)
    private String officePhone;

    @Column(nullable = false)
    private int officePrice;

    @ManyToOne
    @JoinColumn(name = "trip_Id", referencedColumnName = "tripId")
    private Trip trip;
}
