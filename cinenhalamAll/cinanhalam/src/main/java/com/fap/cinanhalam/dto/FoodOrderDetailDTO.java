package com.fap.cinanhalam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

  @ToString
  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  @Getter
  @Setter
  public class FoodOrderDetailDTO extends BaseDTO {
    private Long foodId;
    private Long orderDetailId;
    private int quantity;
    private double price;
  }

