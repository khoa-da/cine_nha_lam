package com.fap.cinanhalam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "ticket_detail")
public class TicketDetailEntity extends BaseEntity{

  @Column(name = "price")
  private double price;

  @OneToOne
  @JoinColumn(name = "ticket_id", unique = true)
  private TicketEntity ticket;

    @ManyToOne
    @JoinColumn(name = "order_detail_id")
    private OrderDetailEntity orderDetail;
}