package com.bookings.appreservas.repository;

import com.bookings.appreservas.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long>{
    @Query("Select b from Booking b where b.checkinDate =:checkin and b.checkoutDate =:checkout")
    public List<Booking> find(@Param("checkin")Date checking_date,
                              @Param("checkout")Date checkout_date);
}
