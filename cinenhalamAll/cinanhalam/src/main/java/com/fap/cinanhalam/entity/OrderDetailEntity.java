package com.fap.cinanhalam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
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
@Table(name = "order_detail")
public class OrderDetailEntity extends BaseEntity {

  @Column(name = "price", nullable = false)
  private Double price;

  @OneToMany(mappedBy = "orderDetail")
  private List<TicketDetailEntity> ticketDetailEntities = new ArrayList<>();

  @OneToMany(mappedBy = "orderDetail")
  private List<FoodOrderDetailEntity> foodOrderDetails = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = true)
  private OrderEntity order;

  @PrePersist
  public void preDetail() {
    this.price = 0.0;
  }
}

