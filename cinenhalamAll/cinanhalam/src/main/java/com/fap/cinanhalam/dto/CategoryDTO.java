package com.fap.cinanhalam.dto;

import jakarta.persistence.Column;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO extends BaseDTO{

    private String name;

    private String description;
}
