package com.fap.cinanhalam.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodDTO extends BaseDTO{
    private String name;
    private String description;
    private String quantity;
    private String price;
    private String type;
    private String size;
}
