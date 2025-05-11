package com.amerkhaled.bookingsystem.domain.dto;

import com.amerkhaled.bookingsystem.domain.entities.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookingRequestDto(
        Long hotelId,
        Long flightId,
        String userName,
        LocalDateTime bookingDate,
        BigDecimal totalPrice,
        PaymentStatus paymentStatus
) {
}
