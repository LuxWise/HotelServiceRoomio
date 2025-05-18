package com.HotelService.service;

import com.HotelService.model.Hotel.Room;
import com.HotelService.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room getHotelById(UUID id){
        return  roomRepository.findById(id).orElse(null);
    }

}
