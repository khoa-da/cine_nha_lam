package com.fap.cinanhalam.entity;

import com.fap.cinanhalam.converter.DateConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "ticket")
public class TicketEntity extends BaseEntity{

    @Temporal(TemporalType.DATE) // Specify that only the date part should be stored
    @Column(name = "release_date", updatable = false)
    @CreationTimestamp
    @Convert(converter = DateConverter.class) // Use a custom converter
    private Date screeningTime;

    @Column(name = "price", nullable = true)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = true)
    private ScheduleEntity schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private UserEntity user;

    @OneToOne(mappedBy = "ticket",fetch = FetchType.LAZY)
    private TicketDetailEntity ticketDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = true, unique = true)
    private SeatEntity seat;

}


