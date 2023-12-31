package com.fap.cinanhalam.dto;

import java.util.List;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO extends BaseDTO{
    private Double totalPrice;
    private List<OrderDetailDTO> orderDetails;
    private Long userId;
    private String orderStatus;
}
