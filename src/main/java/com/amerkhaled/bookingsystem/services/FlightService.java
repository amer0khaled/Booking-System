package com.amerkhaled.bookingsystem.services;

import com.amerkhaled.bookingsystem.domain.entities.Flight;

import java.util.List;

public interface FlightService {

    Flight addFlight(Flight flight);
    List<Flight> getAllFlights();
    Flight getFlightById(Long id);
    void deleteFlight(Long id);
    Flight updateFlight(Long id, Flight flightDetails);


}
