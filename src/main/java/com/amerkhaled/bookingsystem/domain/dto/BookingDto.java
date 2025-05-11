package com.amerkhaled.bookingsystem.domain.dto;

import com.amerkhaled.bookingsystem.domain.entities.Flight;
import com.amerkhaled.bookingsystem.domain.entities.Hotel;
import com.amerkhaled.bookingsystem.domain.entities.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookingDto(
        Long id,
        Hotel hotel,
        Flight flight,
        String userName,
        LocalDateTime bookingDate,
        BigDecimal totalPrice,
        PaymentStatus paymentStatus
) {
}
