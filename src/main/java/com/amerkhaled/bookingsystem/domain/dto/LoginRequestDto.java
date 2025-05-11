package com.amerkhaled.bookingsystem.domain.dto;

public record LoginRequestDto(
        String email,
        String password
) {
}
