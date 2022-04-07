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
@Table(name = "tbl_Parking_Lot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int parkId;

    private int parkArea;

    @Column(nullable = false)
    private String parkName;

    @Column(nullable = false)
    private String parkPlace;

    @Column(nullable = true)
    private int parkPrice;

    private String parkStatus;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private Set<Car> carSet;
}
