package com.fap.cinanhalam.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;
import java.util.List;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "schedule")
public class ScheduleEntity extends BaseEntity{


    @Column(name = "screening_date")
    private Date screeningDate;

    @Column(name="start_hour")
    private Integer startHour;

    @Column(name="end_hour")
    private Integer endHour;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private FilmEntity film;

    @OneToMany(mappedBy = "schedule")
    private List<TicketEntity> tickets;

}
