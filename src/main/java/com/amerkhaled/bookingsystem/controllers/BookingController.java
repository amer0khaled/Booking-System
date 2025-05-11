package com.amerkhaled.bookingsystem.controllers;

import com.amerkhaled.bookingsystem.domain.dto.BookingDto;
import com.amerkhaled.bookingsystem.domain.dto.BookingRequestDto;
import com.amerkhaled.bookingsystem.domain.entities.Booking;
import com.amerkhaled.bookingsystem.exceptions.BadRequestException;
import com.amerkhaled.bookingsystem.mappers.BookingMapper;
import com.amerkhaled.bookingsystem.services.BookingService;
import com.amerkhaled.bookingsystem.services.FlightService;
import com.amerkhaled.bookingsystem.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking-system/bookings")
public class BookingController {

    private BookingService bookingService;
    private BookingMapper bookingMapper;
    private HotelService hotelService;
    private FlightService flightService;

    @Autowired
    public BookingController(BookingService bookingService,
                             BookingMapper bookingMapper,
                             HotelService hotelService,
                             FlightService flightService) {

        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
        this.hotelService = hotelService;
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        Booking booking = bookingMapper.fromDto(bookingRequestDto);

        // Fetch and set hotel and flight
        booking.setHotel(hotelService.getHotelById(bookingRequestDto.hotelId()));
        booking.setFlight(flightService.getFlightById(bookingRequestDto.flightId()));

        Booking createdBooking = bookingService.createBooking(booking);
        BookingDto createdBookingDto = bookingMapper.toDto(createdBooking);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBookingDto);
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        return ResponseEntity.ok(
                bookingService.getAllBookings().stream()
                .map(booking -> bookingMapper.toDto(booking))
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        BookingDto bookingDto = bookingMapper.toDto(booking);
        return ResponseEntity.ok(bookingDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable Long id,
                                                    @RequestBody BookingRequestDto bookingDetailsDto) {

        Booking bookingToUpdate = bookingMapper.fromDto(bookingDetailsDto);

        // Set the ID manually from the path variable
        bookingToUpdate.setId(id);

        // Set related entities from IDs in the request
        bookingToUpdate.setHotel(hotelService.getHotelById(bookingDetailsDto.hotelId()));
        bookingToUpdate.setFlight(flightService.getFlightById(bookingDetailsDto.flightId()));

        Booking updatedBooking = bookingService.updateBooking(id, bookingToUpdate);
        BookingDto updatedBookingDto = bookingMapper.toDto(updatedBooking);
        return ResponseEntity.ok(updatedBookingDto);
    }


    @PutMapping("/payment/{id}")
    public ResponseEntity<BookingDto> updatePaymentStatus(@PathVariable Long id,
                                                          @RequestBody BookingRequestDto bookingDetailsDto) {

        // Only extract the PaymentStatus from the request
        var updatedPayment = bookingService.updatePaymentStatus(id, bookingDetailsDto.paymentStatus());
        var updatedBookingDto = bookingMapper.toDto(updatedPayment);
        return ResponseEntity.ok(updatedBookingDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled successfully");
    }

    @PutMapping("/cancel-hotel/{id}")
    public ResponseEntity<BookingDto> cancelHotelBooking(@PathVariable Long id) {
        Booking booking = bookingService.cancelHotelBooking(id);
        BookingDto bookingDto = bookingMapper.toDto(booking);
        return ResponseEntity.ok(bookingDto);
    }

    @PutMapping("/cancel-flight/{id}")
    public ResponseEntity<BookingDto> cancelFlightBooking(@PathVariable Long id) {
        Booking booking = bookingService.cancelFlightBooking(id);
        BookingDto bookingDto = bookingMapper.toDto(booking);
        return ResponseEntity.ok(bookingDto);
    }

}
