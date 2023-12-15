package com.fap.cinanhalam.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilmCategoryDTO extends BaseDTO{
    private Long filmId;
    private Long categoryId;
    private String filmName;
    private String categoryName;
}
