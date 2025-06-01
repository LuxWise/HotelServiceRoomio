package com.HotelService.service;

import com.HotelService.controller.Room.RoomResponse;
import com.HotelService.dto.Room_dto;
import com.HotelService.model.Hotel.Hotel;
import com.HotelService.model.Room.Room;
import com.HotelService.model.Room.RoomMedia;
import com.HotelService.model.Room.RoomType;
import com.HotelService.repository.HotelRepository;
import com.HotelService.repository.RoomMediaRepository;
import com.HotelService.repository.RoomRepository;
import com.HotelService.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final HotelRepository hotelRepository;
    private final RoomMediaRepository roomMediaRepository;
    private final AzureBlobStorage azureBlobStorage;

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room getHotelById(UUID id){
        return  roomRepository.findById(id).orElse(null);
    }

    public RoomResponse createRoom(Room_dto room){
        RoomType roomType = roomTypeRepository.findById(room.getRoomTypeId())
                .orElseThrow(() -> new RuntimeException("Room type not found with id"));

        Hotel hotel = hotelRepository.findById(room.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found with id"));

        Room room_data = getRoomData(room, roomType, hotel);

        roomRepository.save(room_data);
        return RoomResponse.builder().message("Room created").build();
    }

    public RoomResponse createMediaRoom(UUID roomId, List<MultipartFile> media) throws IOException {
        Room room =  roomRepository.findById(roomId).orElse(null);

        List<String> mediaUrls = azureBlobStorage.updateMultipartFiles(media,roomId);

        for (String mediaUrl : mediaUrls){
            RoomMedia room_media_data = new RoomMedia();
            room_media_data.setRoomMediaLink(mediaUrl);
            room_media_data.setRoomId(room);
            room_media_data.setCreatedAt(LocalDateTime.now());

            roomMediaRepository.save(room_media_data);
        }

        return RoomResponse.builder().message("Room media created").build();
    }

    private static Room getRoomData(Room_dto room, RoomType roomType, Hotel hotel) {
        Room room_data = new Room();
        room_data.setName(room.getName());
        room_data.setDescription(room.getDescription());
        room_data.setNumberOfBeds(room.getNumberOfBeds());
        room_data.setNumberOfBathrooms(room.getNumberOfBathrooms());
        room_data.setNumberOfPeople(room.getNumberOfPeople());
        room_data.setNumberOfPets(room.getNumberOfPets());
        room_data.setNumberOfKids(room.getNumberOfKids());
        room_data.setAvailableKids(room.isAvailableKids());
        room_data.setAvailablePets(room.isAvailablePets());
        room_data.setPrice(room.getPrice());
        room_data.setRoomTypetId(roomType);
        room_data.setHotelId(hotel);
        room_data.setCreatedAt(LocalDateTime.now());
        room_data.setActive(true);
        return room_data;
    }
}
