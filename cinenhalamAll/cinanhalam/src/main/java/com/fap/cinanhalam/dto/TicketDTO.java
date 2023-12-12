package com.fap.cinanhalam.dto;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketDTO extends BaseDTO {

  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date screeningTime;

  private Double price;

  private Long scheduleId;

  private Long seatId;
}
