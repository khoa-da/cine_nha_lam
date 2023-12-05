package com.fap.cinanhalam.dto;

import com.fap.cinanhalam.entity.FilmEntity;
import com.fap.cinanhalam.entity.TicketEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Date;
import java.util.List;


public class ScheduleDTO extends BaseDTO{
    private Date screeningDate;
    private Integer startHour;
    private Integer endHour;

}
