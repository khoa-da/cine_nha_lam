package com.fap.cinanhalam.dto;

import lombok.*;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilmDTO extends BaseDTO{
    private String name;
    private Double price;
    private String description;
    private String director;
    private String actor;
    private Date releaseDate;
    private Integer duration;
    private String language;
    private Double rating;
    private String img;
    private String genre;
    private String trailerUrl;
}
