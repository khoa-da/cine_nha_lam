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
    private Date screeningTime; //thời gian chiếu chi tiết, ví dụ 3h ngày 5/11

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private ScheduleEntity schedule;    //thời gian chiếu của 1 ngày

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private RoomEntity room;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id", referencedColumnName = "id")
    private OrderDetailEntity orderDetail;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_number", nullable = false, unique = true)
    private SeatEntity seatNumber;

}

enum TicketStatus {
    AVAILABLE,
    SOLD,
    RESERVED,
    CANCELLED
}
