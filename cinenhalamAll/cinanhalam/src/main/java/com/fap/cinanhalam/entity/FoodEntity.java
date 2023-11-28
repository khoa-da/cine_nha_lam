package com.fap.cinanhalam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
public class FoodEntity extends BaseEntity{

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "quantity")
  private String quantity;

  @Column(name = "price")
  private String price;

  @Column(name = "type")
  private String type;

  @Column(name = "size")
  private String size;



  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_detail_id", nullable = false)
  private OrderDetailEntity orderDetail;

}
