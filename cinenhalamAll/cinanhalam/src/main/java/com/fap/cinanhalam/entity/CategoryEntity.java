package com.fap.cinanhalam.entity;

import jakarta.persistence.Entity;
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
@Table(name = "category")
public class CategoryEntity extends BaseEntity{

}
