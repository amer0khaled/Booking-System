package com.amerkhaled.bookingsystem.mappers;

import com.amerkhaled.bookingsystem.domain.dto.BookingDto;
import com.amerkhaled.bookingsystem.domain.dto.BookingRequestDto;
import com.amerkhaled.bookingsystem.domain.entities.Booking;

public interface BookingMapper {

    Booking fromDto(BookingRequestDto bookingDto);
    BookingDto toDto(Booking booking);

}
