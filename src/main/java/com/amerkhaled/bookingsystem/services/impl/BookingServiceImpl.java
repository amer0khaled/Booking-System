package com.amerkhaled.bookingsystem.services.impl;

import com.amerkhaled.bookingsystem.domain.entities.Booking;
import com.amerkhaled.bookingsystem.domain.entities.Flight;
import com.amerkhaled.bookingsystem.domain.entities.Hotel;
import com.amerkhaled.bookingsystem.domain.entities.PaymentStatus;
import com.amerkhaled.bookingsystem.exceptions.ResourceNotFoundException;
import com.amerkhaled.bookingsystem.repositories.BookingRepository;
import com.amerkhaled.bookingsystem.repositories.FlightRepository;
import com.amerkhaled.bookingsystem.repositories.HotelRepository;
import com.amerkhaled.bookingsystem.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final FlightRepository flightRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              HotelRepository hotelRepository,
                              FlightRepository flightRepository) {
        this.bookingRepository = bookingRepository;
        this.hotelRepository = hotelRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        Booking bookingToCreate = new Booking();

        if (booking.getHotel() != null) {
            Hotel hotel = hotelRepository.findById(booking.getHotel().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + booking.getHotel().getId()));
            bookingToCreate.setHotel(hotel);
            bookingToCreate.setTotalPrice(bookingToCreate.getTotalPrice().add(hotel.getTotalPrice()));
        }

        if (booking.getFlight() != null) {
            Flight flight = flightRepository.findById(booking.getFlight().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + booking.getFlight().getId()));
            bookingToCreate.setFlight(flight);
            bookingToCreate.setTotalPrice(bookingToCreate.getTotalPrice().add(flight.getPrice()));
        }

        bookingToCreate.setPaymentStatus(PaymentStatus.PENDING);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }

    @Override
    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking bookingToUpdate = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

        if (bookingDetails.getHotel() != null) {
            Hotel hotel = hotelRepository.findById(bookingDetails.getHotel().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + bookingDetails.getHotel().getId()));
            bookingToUpdate.setHotel(hotel);
            bookingToUpdate.setTotalPrice(bookingToUpdate.getTotalPrice().add(hotel.getTotalPrice()));
        }

        if (bookingDetails.getFlight() != null) {
            Flight flight = flightRepository.findById(bookingDetails.getFlight().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + bookingDetails.getFlight().getId()));
            bookingToUpdate.setFlight(flight);
            bookingToUpdate.setTotalPrice(bookingToUpdate.getTotalPrice().add(flight.getPrice()));
        }

        if (bookingDetails.getPaymentStatus() != null) {
            bookingToUpdate.setPaymentStatus(bookingDetails.getPaymentStatus());
        }

        return bookingRepository.save(bookingToUpdate);

    }

    @Override
    public Booking updatePaymentStatus(Long id, PaymentStatus paymentStatus) {
        Booking bookingToUpdate = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
        bookingToUpdate.setPaymentStatus(paymentStatus);
        return bookingRepository.save(bookingToUpdate);
    }

    @Override
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
        bookingRepository.delete(booking);

    }

    @Override
    public Booking cancelHotelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

        if (booking.getHotel() != null) {
            Hotel hotel = hotelRepository.findById(booking.getHotel().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + booking.getHotel().getId()));
            hotel.setTotalRooms(hotel.getTotalRooms() + 1);
            hotelRepository.save(hotel);
        }
        booking.setTotalPrice(booking.getTotalPrice().subtract(booking.getHotel().getTotalPrice()));
        booking.setHotel(null);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking cancelFlightBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

        if (booking.getFlight() != null) {
            Flight flight = flightRepository.findById(booking.getFlight().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + booking.getFlight().getId()));
            flight.setTotalSeats(flight.getTotalSeats() + 1);
            flightRepository.save(flight);
        }
        booking.setTotalPrice(booking.getTotalPrice().subtract(booking.getFlight().getPrice()));
        booking.setFlight(null);
        return bookingRepository.save(booking);
    }


}
