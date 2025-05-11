package com.amerkhaled.bookingsystem.mappers;

import com.amerkhaled.bookingsystem.domain.dto.FlightDto;
import com.amerkhaled.bookingsystem.domain.entities.Flight;
import org.springframework.stereotype.Component;

@Component
public interface FlightMapper {

    Flight fromDto(FlightDto flightDto);
    FlightDto toDto(Flight flight);

}
