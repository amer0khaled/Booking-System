package com.amerkhaled.bookingsystem.services;

import com.amerkhaled.bookingsystem.domain.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel addHotel(Hotel hotel);
    Hotel getHotelById(Long id);
    List<Hotel> getAllHotels();
    Hotel updateHotel(Long id, Hotel hotelDetails);
    void deleteHotel(Long id);

}
