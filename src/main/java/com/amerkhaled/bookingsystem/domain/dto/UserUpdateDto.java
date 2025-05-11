package com.amerkhaled.bookingsystem.domain.dto;

import com.amerkhaled.bookingsystem.domain.entities.UserRole;

public record UserUpdateDto(
    Long id,
    String name,
    String email,
    String password,
    UserRole role
) {}
