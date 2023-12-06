package com.fap.cinanhalam.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CinemaDTO extends BaseDTO{

    private String name;

    private String location;

    private int capacity; // Sức chứa tổng cộng của rạp chiếu phim

    private String contactNumber;

    private String website;

    private String openingHours;

    private String provinceName;
}
