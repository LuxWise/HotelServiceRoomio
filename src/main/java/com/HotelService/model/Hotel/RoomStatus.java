package com.HotelService.model.Hotel;

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
@Table(name = "room_status")
public class RoomStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "visible", nullable = false)
    private boolean visible;

}
