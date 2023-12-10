package com.fap.cinanhalam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "food")
public class FoodEntity extends BaseEntity {

  @NotNull
  @Column(name = "name")
  private String name;

  @NotBlank
  @Column(name = "description")
  private String description;

  @NotNull
  @Column(name = "quantity")
  private Integer stock;

  @NotNull
  @Column(name = "price")
  private Double price;

  @NotBlank
  @Column(name = "type")
  private String type;

  @NotBlank
  @Column(name = "size")
  private String size;

  @NotBlank
  @Column(name = "image")
  private String image;

  @OneToMany(mappedBy = "food")
  private List<FoodOrderDetailEntity> foodOrderDetails = new ArrayList<>();

}
