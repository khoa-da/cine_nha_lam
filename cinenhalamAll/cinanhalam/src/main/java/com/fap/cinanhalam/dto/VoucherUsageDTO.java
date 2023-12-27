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
public class VoucherUsageDTO extends BaseDTO{
  private Long orderDetailId;
  private  Long voucherId;
  private String code;

}
