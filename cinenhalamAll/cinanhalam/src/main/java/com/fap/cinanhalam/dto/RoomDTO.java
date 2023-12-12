package com.fap.cinanhalam.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomDTO extends BaseDTO{
    private String roomNumber;
    private Integer totalSeats;
}
