package com.fap.cinanhalam.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilmCinemaDTO extends BaseDTO{
    private Long cinemaId;
    private String cinemaName;
    private Long scheduleId;
    private Date scheduleScreeningDate;
    private LocalTime scheduleStartHour;
    private LocalTime scheduleEndHour;
}
