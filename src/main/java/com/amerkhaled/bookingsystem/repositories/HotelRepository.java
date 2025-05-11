package com.amerkhaled.bookingsystem.repositories;

import com.amerkhaled.bookingsystem.domain.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
