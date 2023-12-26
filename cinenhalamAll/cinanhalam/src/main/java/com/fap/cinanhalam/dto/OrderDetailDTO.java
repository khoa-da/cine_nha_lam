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
    private List<FoodOrderDetailOutputDTO> foodList;
    private List<TicketDetailDTO> ticketList;
    private List<VoucherUsageDTO> voucherUsages;
    private Long orderId;
}
