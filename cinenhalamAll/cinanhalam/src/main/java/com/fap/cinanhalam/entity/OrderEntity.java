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

    @OneToMany(mappedBy = "orderId")
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @Column(name="status")
    private String status;

    @Column(name="total_price")
    private Double totalPrice;

    @Column(name="quantity")
    private int quantity;

}
