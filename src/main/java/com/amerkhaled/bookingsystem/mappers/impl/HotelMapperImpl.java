package com.amerkhaled.bookingsystem.mappers.impl;

import com.amerkhaled.bookingsystem.domain.dto.HotelDto;
import com.amerkhaled.bookingsystem.domain.entities.Hotel;
import com.amerkhaled.bookingsystem.mappers.HotelMapper;
import org.springframework.stereotype.Component;

@Component
public class HotelMapperImpl implements HotelMapper {

    @Override
    public Hotel fromDto(HotelDto hotelDto) {
        return new Hotel(
                hotelDto.id(),
                hotelDto.name(),
                hotelDto.address(),
                hotelDto.totalRooms(),
                hotelDto.rating(),
                hotelDto.totalPrice()
        );
    }

    @Override
    public HotelDto toDto(Hotel hotel) {
        return new HotelDto(
                hotel.getId(),
                hotel.getName(),
                hotel.getAddress(),
                hotel.getTotalRooms(),
                hotel.getRating(),
                hotel.getTotalPrice()
        );
    }
}
