package com.bookings.appreservas.service;

import com.bookings.appreservas.entities.Booking;

import java.util.Date;
import java.util.List;

public interface IBookingService extends CrudService<Booking>{
    public List<Booking> find( Date checking_date, Date checkout_date) throws Exception;
}
