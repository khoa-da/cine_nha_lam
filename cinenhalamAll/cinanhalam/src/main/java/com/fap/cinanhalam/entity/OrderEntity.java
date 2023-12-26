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
@Table(name = "orders")
public class OrderEntity extends BaseEntity{

    @Column(name="total_price", nullable = false)
    private Double totalPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private UserEntity user;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @PrePersist
    public void preOrder() {
        this.totalPrice = 0.0;
        this.orderStatus = OrderStatus.NOT_PAY;
    }

}
