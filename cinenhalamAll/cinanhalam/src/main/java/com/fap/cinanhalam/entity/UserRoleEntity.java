package com.fap.cinanhalam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "user_role")
public class UserRoleEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private RoleEntity role;

}
