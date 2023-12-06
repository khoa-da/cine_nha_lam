package com.fap.cinanhalam.dto;

import java.util.Date;
import lombok.*;


public class VoucherDTO {
  private String name;
  private String code;
  private String description;
  private Double value;
  private Date startDate;
  private Date endDate;
  private Boolean status;
  private int quantity;
}
