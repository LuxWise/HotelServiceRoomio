package com.HotelService.service;

import com.HotelService.model.Hotel.Hotel;
import com.HotelService.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelService {

    HotelRepository hotelRepository;

    public List<Hotel> getAllHotels(){
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(UUID id){
        return hotelRepository.findById(id).orElse(null);
    }
}
