package com.fap.cinanhalam.dto;

import lombok.*;

import java.util.Date;
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilmCinemaDTO extends BaseDTO{
    private Date screeningTime;
}
