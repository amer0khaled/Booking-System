package com.amerkhaled.bookingsystem.services;

import com.amerkhaled.bookingsystem.domain.entities.Booking;
import com.amerkhaled.bookingsystem.domain.entities.PaymentStatus;

import java.util.List;

public interface BookingService {

    Booking createBooking(Booking booking);
    List<Booking> getAllBookings();
    Booking getBookingById(Long id);
    Booking updateBooking(Long id, Booking bookingDetails);
    Booking updatePaymentStatus(Long id, PaymentStatus paymentStatus);
    void cancelBooking(Long id);
    Booking cancelHotelBooking(Long id);
    Booking cancelFlightBooking(Long id);

}
