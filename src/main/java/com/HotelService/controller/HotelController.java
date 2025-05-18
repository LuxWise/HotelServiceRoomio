package com.HotelService.controller;

import com.HotelService.model.Hotel.Hotel;
import com.HotelService.model.Hotel.Room;
import com.HotelService.service.HotelService;
import com.HotelService.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.util.function.ThrowingSupplier;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;
    private final RoomService roomService;

    @GetMapping("/health")
    public ResponseEntity<String> checkHeaders(@RequestHeader(value = "X-User-ID") String user) {
        System.out.println("✅ Header X-User-ID: " + user);
        return ResponseEntity.ok("UserID: " + user);
    }

    @GetMapping()
    public ResponseEntity<Object[]> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels.toArray());
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable UUID hotelId) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotel);
    }

        @GetMapping("/rooms")
    public ResponseEntity<Object[]> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms.toArray());
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable UUID roomId) {
        Room room = roomService.getHotelById(roomId);
        return ResponseEntity.ok(room);
    }

    @PostMapping()
    public ResponseEntity<HotelResponse> createHotel() {
        return  handleRequestProcess(() -> {
            return HotelResponse.builder().message("Hotel created successfully").build();
        });
    }

    @PostMapping("/rooms")
    public ResponseEntity<HotelResponse> createRooms() {
        return  handleRequestProcess(() -> {
            return HotelResponse.builder().message("Room created successfully").build();
        });
    }

    @PatchMapping("/rooms/{hotelId}")
    public ResponseEntity<HotelResponse> modifyRooms() {
        return handleRequestProcess(() -> {
            return HotelResponse.builder().message("Room modify successfully").build();
        });
    }

    private ResponseEntity<HotelResponse> handleRequestProcess(ThrowingSupplier<HotelResponse> supplier) {
        try {
            return ResponseEntity.ok(supplier.get());
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body(HotelResponse.builder().message("Error to send email").build());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(HotelResponse.builder().message("Internal Server Error").build());
        }
    }
}
