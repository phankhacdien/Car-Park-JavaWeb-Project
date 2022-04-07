package com.sherlock.carpark.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "tbl_Ticket",
        uniqueConstraints = {
                @UniqueConstraint(name = "Customer_Name_Unique", columnNames = "Customer_Name"),
                @UniqueConstraint(name = "License_plate_Unique", columnNames = "license_Plate")
        }
)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;
    private LocalTime bookingTime;

    @Column(name = "Customer_Name", nullable = false)
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "license_Plate", referencedColumnName = "licensePlate")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "trip_Id", referencedColumnName = "tripId")
    private Trip trip;
}
