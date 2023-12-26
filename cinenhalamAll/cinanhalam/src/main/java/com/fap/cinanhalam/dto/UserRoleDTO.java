package com.fap.cinanhalam.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRoleDTO extends BaseDTO{
    private Long userId;
    private String userUserName;
    private Long roleId;
    private String roleName;
}
