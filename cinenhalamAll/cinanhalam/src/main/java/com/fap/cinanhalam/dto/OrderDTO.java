package com.fap.cinanhalam.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO extends BaseDTO{

    private Long userId;
    private Double totalPrice;
    private int quantity;

    private Long promotionID;

    public String vnp_OrderInfo = "Film";

    public String vnp_OrderType = "200000";
    public String vnp_TxnRef;
}
