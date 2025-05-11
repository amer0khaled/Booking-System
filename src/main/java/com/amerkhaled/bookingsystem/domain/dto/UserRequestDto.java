package com.amerkhaled.bookingsystem.domain.dto;

import com.amerkhaled.bookingsystem.domain.entities.UserRole;


public record UserRequestDto(
        String name,
        String email,
        String password,
        UserRole role
) {}
