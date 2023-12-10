package com.fap.cinanhalam.dto;

import lombok.*;

import java.util.Date;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseDTO {
    private Long id;
    private Date createdDate = new Date();
    private Boolean status = true;
}
