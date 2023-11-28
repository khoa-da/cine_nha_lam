package com.fap.cinanhalam.entity;

import jakarta.persistence.*;
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
@Table(name = "order")
public class OrderEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name="status", nullable = false)
    private String status;

    @Column(name="total_price", nullable = false)
    private Double totalPrice;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @OneToMany(mappedBy = "order")
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();

    @OneToMany(mappedBy = "order")
    private List<VoucherUsageEntity> voucherUsages = new ArrayList<>();


}
