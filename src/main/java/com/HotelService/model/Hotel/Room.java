package com.HotelService.model.Hotel;

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

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomTypetId;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel statusId;

    @Column(name = "active", nullable = false)
    private boolean active;

}
