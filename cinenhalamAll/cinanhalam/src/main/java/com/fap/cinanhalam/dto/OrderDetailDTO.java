package com.fap.cinanhalam.dto;

import java.util.List;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDTO extends BaseDTO{
    private Double price;
    private int quantity;
    private Long ticketId;
    private Long orderId;
}
