package com.fap.cinanhalam.dto;

import com.fap.cinanhalam.entity.RoomEntity;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeatDTO extends BaseDTO{
    private String seatNumber;
    private String seatType;
    private Long roomId;
}
