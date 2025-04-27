package com.HotelService.repository;

import com.HotelService.model.Hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface HotelRepository  extends JpaRepository<Hotel, UUID> {
}
