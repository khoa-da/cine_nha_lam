package com.fap.cinanhalam.entity;

import com.fap.cinanhalam.converter.DateConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "voucher")
public class VoucherEntity extends BaseEntity{

  @Column(name = "name", nullable = false)
  private String name;

  @NotBlank
  @Size(max=30)
  @Column(name = "code", unique = true)
  private String code;

  @Column(name = "description", nullable = false)
  private String description;

  @Unsigned
  @Column(name = "value")
  private Double value;

  @Column(name = "needPrice")
  private Double requirePrice;


  @Temporal(TemporalType.DATE) // Specify that only the date part should be stored
  // Specify that only the date part should be stored
  @Column(name = "start_date")
  @Convert(converter = DateConverter.class) // Use a custom converter
  private Date startDate;

  @Temporal(TemporalType.DATE)
  @Column(name = "end_date")
  @Convert(converter = DateConverter.class)
  private Date endDate;

  @Column(name="quanity")
  private int quantity;

  @OneToMany(mappedBy = "voucher")
  private List<VoucherUsageEntity> voucherUsages = new ArrayList<>();
}
