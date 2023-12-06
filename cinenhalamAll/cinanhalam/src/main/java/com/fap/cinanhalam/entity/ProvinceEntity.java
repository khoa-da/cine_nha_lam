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
@Table(name = "province")
public class ProvinceEntity extends BaseEntity{

    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "province")
    private List<CinemaEntity> cinemas = new ArrayList<>();

}