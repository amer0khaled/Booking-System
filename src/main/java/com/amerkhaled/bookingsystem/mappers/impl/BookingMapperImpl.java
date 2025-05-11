package com.amerkhaled.bookingsystem.mappers.impl;

import com.amerkhaled.bookingsystem.domain.dto.BookingDto;
import com.amerkhaled.bookingsystem.domain.dto.BookingRequestDto;
import com.amerkhaled.bookingsystem.domain.entities.Booking;
import com.amerkhaled.bookingsystem.mappers.BookingMapper;
import org.springframework.stereotype.Component;

@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public Booking fromDto(BookingRequestDto dto) {
        Booking booking = new Booking();
        booking.setUserName(dto.userName());
        booking.setBookingDate(dto.bookingDate());
        booking.setTotalPrice(dto.totalPrice());
        booking.setPaymentStatus(dto.paymentStatus());
        // Hotel and Flight will need to be set in the service
        return booking;
    }

    @Override
    public BookingDto toDto(Booking booking) {
        return new BookingDto(
                booking.getId(),
                booking.getHotel(),
                booking.getFlight(),
                booking.getUserName(),
                booking.getBookingDate(),
                booking.getTotalPrice(),
                booking.getPaymentStatus()
        );
    }
}
