package com.amerkhaled.bookingsystem.domain.dto;

import java.math.BigDecimal;

public record HotelDto(
        Long id,
        String name,
        String address,
        Integer totalRooms,
        Double rating,
        BigDecimal totalPrice
) {
}
