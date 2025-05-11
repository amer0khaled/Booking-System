package com.amerkhaled.bookingsystem.domain.entities;

public enum PaymentStatus {
    PENDING,       // Payment is initiated but not completed
    COMPLETED,     // Payment was successfully completed
    FAILED,        // Payment attempt failed
    CANCELLED,     // Payment was cancelled by the user or system
    REFUNDED       // Payment was refunded after completion

}
