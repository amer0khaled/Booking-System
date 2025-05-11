package com.amerkhaled.bookingsystem.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FlightDto(
        Long id,
        String airline,
        String departureAirport,
        String arrivalAirport,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        BigDecimal price,
        Integer totalSeats
) {
}
