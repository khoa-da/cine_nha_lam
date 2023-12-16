package com.fap.cinanhalam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "room")
public class RoomEntity extends BaseEntity{

    @OneToMany(mappedBy = "room")
    private List<SeatEntity> seatEntityList;

    @Column(name = "number")
    private String roomNumber;

    @Column(name = "total_seats")
    private Integer totalSeats;


    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinema;
}
