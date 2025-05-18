package com.HotelService.service;

import com.HotelService.controller.Room.RoomResponse;
import com.HotelService.model.Room.Room;
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

    public RoomResponse createRoom(Room room){
        try{
            roomRepository.save(room);
            return RoomResponse.builder().message("Room created").build();
        } catch (Exception e) {
            return RoomResponse.builder().message("Error to create request").build();
        }


    }
}
