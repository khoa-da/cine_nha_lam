package com.fap.cinanhalam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "seat")
public class SeatEntity extends BaseEntity{

    @NotBlank
    @Column(name = "seat_number")
    private String seatNumber;

    @NotBlank
    @Column(name = "seat_type")
    private String seatType;


    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;



}
