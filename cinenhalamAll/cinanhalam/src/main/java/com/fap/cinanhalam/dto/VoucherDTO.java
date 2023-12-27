package com.fap.cinanhalam.dto;

import java.util.Date;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class VoucherDTO extends BaseDTO{
  private String name;
  private String code;
  private String description;
  private Double value;
  private Double requirePrice;
  private Date startDate;
  private Date endDate;
  private int quantity;
}
