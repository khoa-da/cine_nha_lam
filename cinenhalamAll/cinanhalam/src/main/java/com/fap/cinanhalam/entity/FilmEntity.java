package com.fap.cinanhalam.entity;


import com.fap.cinanhalam.converter.DateConverter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "film")
public class FilmEntity extends BaseEntity{

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "director")
    private String director;

    @Temporal(TemporalType.DATE) // Specify that only the date part should be stored
    @Column(name = "release_date", updatable = false)
    @CreationTimestamp
    @Convert(converter = DateConverter.class) // Use a custom converter
    private Date releaseDate;

    @Column(name = "duration")
    private Integer duration; // Duration in minutes

    @Column(name = "language")
    private String language;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "img")
    private String img;

    @Column(name="status")
    private Boolean status;

    @OneToMany(mappedBy = "film")
    private List<FilmCinemaEntity> filmCinemas = new ArrayList<>();

}
