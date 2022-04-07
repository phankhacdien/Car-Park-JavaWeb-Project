package com.sherlock.carpark.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_Car")
public class Car {
    @Id
    private String licensePlate;

    @Column(nullable = false)
    private String carColor;

    private String carType;
    private String company;

    @ManyToOne
    @JoinColumn(name = "park_id", referencedColumnName = "parkId")
    private ParkingLot parkingLot;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = CascadeType.ALL)
    private Set<Ticket> ticketSet;
}
