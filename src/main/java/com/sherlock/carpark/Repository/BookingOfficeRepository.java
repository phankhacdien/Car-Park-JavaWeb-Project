package com.sherlock.carpark.Repository;

import com.sherlock.carpark.Entity.BookingOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingOfficeRepository extends JpaRepository<BookingOffice, Integer> {
}
