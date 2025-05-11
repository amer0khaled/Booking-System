package com.amerkhaled.bookingsystem.domain.entities;

public enum UserRole {

    ADMIN,     // Full access to system management
    MANAGER,   // Manages specific resources (hotels or flights)
    CUSTOMER,  // Regular user who makes bookings
    AGENT      // Makes bookings on behalf of customers

}
