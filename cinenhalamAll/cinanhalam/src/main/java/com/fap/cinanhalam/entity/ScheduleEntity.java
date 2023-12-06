package com.fap.cinanhalam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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


    @Temporal(TemporalType.DATE)
    @Column(name = "screening_date")
    private Date screeningDate;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_hour")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date startHour;

    @Temporal(TemporalType.TIME)
    @Column(name = "end_hour")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date endHour;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private FilmEntity film;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    private List<TicketEntity> tickets;

}
