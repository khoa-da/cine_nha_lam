package com.fap.cinanhalam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "food_order_detail")
public class FoodOrderDetailEntity extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "food_id", nullable = true)
  private FoodEntity food;

  @ManyToOne
  @JoinColumn(name = "order_detail_id", nullable = true)
  private OrderDetailEntity orderDetail;

  @Column(name = "quantity", nullable = true)
  private int quantity;

  @Column(name = "price", nullable = true)
  private double price;

}
