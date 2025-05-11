package com.amerkhaled.bookingsystem.mappers;

import com.amerkhaled.bookingsystem.domain.dto.HotelDto;
import com.amerkhaled.bookingsystem.domain.entities.Hotel;

public interface HotelMapper {

    Hotel fromDto(HotelDto hotelDto);
    HotelDto toDto(Hotel hotel);

}
