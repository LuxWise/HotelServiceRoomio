package com.HotelService.model.Room;

import com.HotelService.model.Hotel.Hotel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column( name = "room_name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "number_of_beds", nullable = false)
    private int numberOfBeds;

    @Column(name = "number_of_bathrooms", nullable = false)
    private int numberOfBathrooms;

    @Column(name = "number_of_people", nullable = false)
    private int numberOfPeople;

    @Column(name = "number_of_pets")
    private int numberOfPets;

    @Column(name = "number_of_kids")
    private int numberOfKids;

    @Column(name = "available_kids", nullable = false)
    private boolean availableKids;

    @Column(name = "available_pets", nullable = false)
    private boolean availablePets;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomTypetId;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotelId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;

    @Column(name = "active", nullable = false)
    private boolean active;

}
