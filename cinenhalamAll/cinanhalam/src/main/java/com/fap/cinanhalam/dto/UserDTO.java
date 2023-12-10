package com.fap.cinanhalam.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO extends BaseDTO{
    private String userName;
    private String password;
    private String email;
    private String fullName;
    private String imgUrl;
    private Boolean gender;
    private String dob;
}
