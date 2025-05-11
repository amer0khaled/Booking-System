package com.amerkhaled.bookingsystem.services.impl;

import com.amerkhaled.bookingsystem.domain.entities.Flight;
import com.amerkhaled.bookingsystem.exceptions.ResourceNotFoundException;
import com.amerkhaled.bookingsystem.repositories.FlightRepository;
import com.amerkhaled.bookingsystem.services.FlightService;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));
    }

    @Override
    public Flight updateFlight(Long id, Flight flightDetails) {

        Flight flightToUpdate = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));

        flightToUpdate.setAirline(flightDetails.getAirline());
        flightToUpdate.setDepartureAirport(flightDetails.getDepartureAirport());
        flightToUpdate.setArrivalAirport(flightDetails.getArrivalAirport());
        flightToUpdate.setDepartureTime(flightDetails.getDepartureTime());
        flightToUpdate.setArrivalTime(flightDetails.getArrivalTime());
        flightToUpdate.setPrice(flightDetails.getPrice());
        flightToUpdate.setTotalSeats(flightDetails.getTotalSeats());

        return flightRepository.save(flightToUpdate);

    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

}
