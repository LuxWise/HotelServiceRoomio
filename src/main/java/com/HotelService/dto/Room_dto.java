package com.HotelService.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class Room_dto {
    private String name;
    private String description;
    private int numberOfBeds;
    private int numberOfBathrooms;
    private int numberOfPeople;
    private int numberOfPets;
    private int numberOfKids;
    private boolean availableKids;
    private boolean availablePets;
    private double price;
    private Long roomTypeId;
    private UUID hotelId;
    private boolean active;
}
