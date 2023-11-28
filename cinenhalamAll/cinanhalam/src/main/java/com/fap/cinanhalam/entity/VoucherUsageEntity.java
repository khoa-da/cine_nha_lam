package com.fap.cinanhalam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "voucher_usage")
public class VoucherUsageEntity extends BaseEntity{

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  private OrderEntity order;

  @ManyToOne
  @JoinColumn(name = "voucher_id", nullable = false)
  private VoucherEntity voucher;
}
