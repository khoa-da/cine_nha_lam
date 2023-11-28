package com.fap.cinanhalam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class OrderDetailEntity extends BaseEntity{



  @Column(name="total_price", nullable = false)
  private Double totalPrice;

  @Column(name="quantity", nullable = false)
  private int quantity;

  @OneToOne
  @JoinColumn(name = "ticket_id", referencedColumnName = "id", nullable = false)
  private TicketEntity ticket;

  @OneToMany(mappedBy = "orderDetail")
  private List<FoodEntity> foodEntities = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private OrderEntity order;





}
