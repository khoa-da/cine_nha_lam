package com.fap.cinanhalam.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDTO extends BaseDTO{

    private String title;
    private String content;
    private String description;
    private String imageUrl;
    private String startDate;
    private String endDate;
}
