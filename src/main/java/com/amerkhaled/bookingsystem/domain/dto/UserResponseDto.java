package com.amerkhaled.bookingsystem.domain.dto;

import com.amerkhaled.bookingsystem.domain.entities.UserRole;

public record UserResponseDto(
        Long id,
        String name,
        String email,
        UserRole role
) {
}
