package com.HotelService.controller.Hotel;

import com.HotelService.model.Hotel.Hotel;
import com.HotelService.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.util.function.ThrowingSupplier;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/health")
    public ResponseEntity<String> checkHeaders() {
        return ResponseEntity.ok("Hotel Service is up and running");
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

    @PostMapping()
    public ResponseEntity<HotelResponse> createHotel(@RequestBody Hotel hotel) {
        return handleRequestProcess(() -> hotelService.createHotel(hotel));
    }


    private ResponseEntity<HotelResponse> handleRequestProcess(ThrowingSupplier<HotelResponse> supplier) {
        try {
            return ResponseEntity.ok(supplier.get());
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body(HotelResponse.builder().message("Error to send email").build());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(HotelResponse.builder().message("Internal Server Error: " + e ).build());
        }
    }
}
