package com.amerkhaled.bookingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amerkhaled.bookingsystem.domain.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}