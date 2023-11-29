package com.fap.cinanhalam.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDTO extends BaseDTO{
    private Long orderId;
    private Double price;
    private int quantity;
    private Long ticketId;
}
