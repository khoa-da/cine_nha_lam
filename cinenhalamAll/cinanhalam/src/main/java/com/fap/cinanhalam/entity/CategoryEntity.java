package com.fap.cinanhalam.entity;

import jakarta.persistence.*;
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
@Table(name = "category")
public class CategoryEntity extends BaseEntity{

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "name",unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<FilmCategoryEntity> filmCategories = new ArrayList<>();
}
