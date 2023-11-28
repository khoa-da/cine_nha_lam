package com.fap.cinanhalam.entity;

import lombok.*;
import com.fap.cinanhalam.converter.DateConverter;
import jakarta.persistence.*;
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
@Table(name = "film_cinema")
public class FilmCinemaEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", nullable = false)
    private FilmEntity film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id", nullable = false)
    private CinemaEntity cinema;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "screening_time", updatable = false)
    @CreationTimestamp
    @Convert(converter = DateConverter.class)
    private Date screeningTime;         //  thời gian chiếu của bộ phim tại rạp chiếu

}
