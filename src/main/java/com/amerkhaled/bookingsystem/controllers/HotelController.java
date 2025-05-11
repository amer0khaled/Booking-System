package com.amerkhaled.bookingsystem.controllers;

import com.amerkhaled.bookingsystem.domain.dto.HotelDto;
import com.amerkhaled.bookingsystem.domain.entities.Hotel;
import com.amerkhaled.bookingsystem.exceptions.BadRequestException;
import com.amerkhaled.bookingsystem.mappers.HotelMapper;
import com.amerkhaled.bookingsystem.services.HotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking-system/hotels")
public class HotelController {

    private HotelService hotelService;
    private HotelMapper hotelMapper;

    @Autowired
    public HotelController(HotelService hotelService,
                           HotelMapper hotelMapper) {
        this.hotelService = hotelService;
        this.hotelMapper = hotelMapper;
    }

    @PostMapping
    public ResponseEntity<HotelDto> addHotel(@RequestBody HotelDto hotelDto) {
        Hotel hotel = hotelMapper.fromDto(hotelDto);
        Hotel addedHotel = hotelService.addHotel(hotel);
        HotelDto addedHotelDto = hotelMapper.toDto(addedHotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedHotelDto);
    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        return ResponseEntity.ok(
                hotelService.getAllHotels().stream()
                .map(hotel -> hotelMapper.toDto(hotel))
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        HotelDto hotelDto = hotelMapper.toDto(hotel);
        return ResponseEntity.ok(hotelDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDto> updateHotel(@PathVariable Long id,
                                                @RequestBody HotelDto hotelDto) {

        if (!id.equals(hotelDto.id())) {
            throw new BadRequestException("Path ID and HotelDTO ID do not match");
        }

        Hotel hotel = hotelMapper.fromDto(hotelDto);
        Hotel updatedHotel = hotelService.updateHotel(id, hotel);
        HotelDto updatedHotelDto = hotelMapper.toDto(updatedHotel);
        return ResponseEntity.ok(updatedHotelDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok("Hotel deleted successfully");
    }


}
