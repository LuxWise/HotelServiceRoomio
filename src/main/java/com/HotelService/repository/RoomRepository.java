package com.HotelService.repository;

import com.HotelService.model.Hotel.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
}
