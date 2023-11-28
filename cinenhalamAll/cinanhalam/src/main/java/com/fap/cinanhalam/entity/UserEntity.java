package com.fap.cinanhalam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "user")
public class UserEntity extends BaseEntity{
    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "user_name")
    private String userName;

    @NotBlank
    @JsonIgnore
    @Column
    @Size(min = 6)
    private String password;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "img_url")
    private String imgUrl;

    @Column
    private Boolean status;

    @Column
    private Boolean gender;

    @Column
    private String dob;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @OneToMany(mappedBy = "userId")
    private List<OrderEntity> order =new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<FeedbackEntity> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "replyer")
    private List<FeedbackEntity> feedbackRs = new ArrayList<>();
}
