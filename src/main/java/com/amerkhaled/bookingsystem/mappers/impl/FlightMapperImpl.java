package com.amerkhaled.bookingsystem.mappers.impl;

import com.amerkhaled.bookingsystem.domain.dto.FlightDto;
import com.amerkhaled.bookingsystem.domain.entities.Flight;
import com.amerkhaled.bookingsystem.mappers.FlightMapper;
import org.springframework.stereotype.Component;

@Component
public class FlightMapperImpl implements FlightMapper {

    @Override
    public Flight fromDto(FlightDto flightDto) {
        return new Flight(
                flightDto.id(),
                flightDto.airline(),
                flightDto.departureAirport(),
                flightDto.arrivalAirport(),
                flightDto.departureTime(),
                flightDto.arrivalTime(),
                flightDto.price(),
                flightDto.totalSeats()

        );
    }

    @Override
    public FlightDto toDto(Flight flight) {
        return new FlightDto(
                flight.getId(),
                flight.getAirline(),
                flight.getDepartureAirport(),
                flight.getArrivalAirport(),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getPrice(),
                flight.getTotalSeats()
                
        );
    }

    
    
}
