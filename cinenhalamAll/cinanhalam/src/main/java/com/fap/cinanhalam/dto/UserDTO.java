package com.fap.cinanhalam.dto;

import com.fap.cinanhalam.entity.UserEntity;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

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
    private List<Long> roleIds;



}
