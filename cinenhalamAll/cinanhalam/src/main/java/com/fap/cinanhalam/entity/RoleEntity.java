package com.fap.cinanhalam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity{
    @NotBlank
    @Size(max=30)
    @Column(name ="name")
    private String name;

    @NotBlank
    @Size(min = 10, max = 100)
    @Column(name ="description")
    private String description;

//    @OneToMany(mappedBy = "role")
//    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private List<UserRoleEntity> userRoleEntities = new ArrayList<>();

}
