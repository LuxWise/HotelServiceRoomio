package com.HotelService.model.Room;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_type")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code; // Ej: SGL, DBL, STE

    @Column(nullable = false)
    private String name; // Ej: Single, Double, Suite

    @Column(length = 1000)
    private String description;
}
