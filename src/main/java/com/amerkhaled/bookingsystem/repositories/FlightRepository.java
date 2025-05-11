package com.amerkhaled.bookingsystem.repositories;

import com.amerkhaled.bookingsystem.domain.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

}
