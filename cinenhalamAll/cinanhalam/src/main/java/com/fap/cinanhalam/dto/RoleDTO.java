package com.fap.cinanhalam.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleDTO extends BaseDTO{
    private String name;
    private String description;
}
