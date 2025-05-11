package com.amerkhaled.bookingsystem.controllers;

import com.amerkhaled.bookingsystem.domain.dto.FlightDto;
import com.amerkhaled.bookingsystem.domain.entities.Flight;
import com.amerkhaled.bookingsystem.exceptions.BadRequestException;
import com.amerkhaled.bookingsystem.mappers.FlightMapper;
import com.amerkhaled.bookingsystem.services.FlightService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking-system/flights")
public class FlightController {

    private FlightService flightService;
    private FlightMapper flightMapper;

    @Autowired
    public FlightController(FlightService flightService,
                            FlightMapper flightMapper) {

        this.flightService = flightService;
        this.flightMapper = flightMapper;
    }

    @PostMapping
    public ResponseEntity<FlightDto> addFlight(
        @RequestBody FlightDto flightDto) {

        Flight flight = flightMapper.fromDto(flightDto);
        Flight addedFlight = flightService.addFlight(flight);
        FlightDto addedFlightDto = flightMapper.toDto(addedFlight);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedFlightDto);

    }

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return ResponseEntity.ok(
            flightService.getAllFlights().stream()
            .map(flight -> flightMapper.toDto(flight))
            .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(
        @PathVariable Long id) {

        Flight flight = flightService.getFlightById(id);
        FlightDto flightDto = flightMapper.toDto(flight);
        return ResponseEntity.ok(flightDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable Long id,
     @RequestBody FlightDto flightDto) {

        if (!id.equals(flightDto.id())) {
            throw new BadRequestException("Path ID and FlightDTO ID do not match");
        }

        Flight flightToUpdate = flightMapper.fromDto(flightDto);
        Flight updatedFlight = flightService.updateFlight(id, flightToUpdate);
        FlightDto updatedFlightDto = flightMapper.toDto(updatedFlight);
        return ResponseEntity.ok(updatedFlightDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.ok("Flight deleted successfully");
    }


}
