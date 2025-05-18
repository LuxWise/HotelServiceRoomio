package com.HotelService.controller.Room;

import com.HotelService.dto.Room_dto;
import com.HotelService.model.Room.Room;
import com.HotelService.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.util.function.ThrowingSupplier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/hotel/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("")
    public ResponseEntity<Object[]> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms.toArray());
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable UUID roomId) {
        Room room = roomService.getHotelById(roomId);
        return ResponseEntity.ok(room);
    }

    @PostMapping("")
    public ResponseEntity<RoomResponse> createRooms(@RequestBody Room_dto room) {
        return  handleRequestProcess(() -> roomService.createRoom(room));
    }

    @PatchMapping("/{roomId}")
    public ResponseEntity<RoomResponse> modifyRooms() {
        return handleRequestProcess(() -> RoomResponse.builder().message("Room modify successfully").build());
    }

    private ResponseEntity<RoomResponse> handleRequestProcess(ThrowingSupplier<RoomResponse> supplier) {
        try {
            return ResponseEntity.ok(supplier.get());
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body(RoomResponse.builder().message("Error to send email").build());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(RoomResponse.builder().message("Internal Server Error: " + e ).build());
        }
    }
}
