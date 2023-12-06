package com.fap.cinanhalam.entity;

import jakarta.persistence.Entity;
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
  @JoinColumn(name = "food_id", nullable = false)
  private FoodEntity food;

  @ManyToOne
  @JoinColumn(name = "order_detail_id", nullable = false)
  private OrderDetailEntity orderDetail;

}
