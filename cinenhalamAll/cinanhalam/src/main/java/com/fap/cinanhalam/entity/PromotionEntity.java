//package com.fap.cinanhalam.entity;
//
//import com.fap.cinanhalam.converter.DateConverter;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import jdk.jfr.Unsigned;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@ToString
//@Builder
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//
//
//@Entity
//@Table(name = "promotion")
//public class PromotionEntity extends BaseEntity{
//    @NotBlank
//    @Size(max=30)
//    @Column(name = "code", unique = true)
//    private String code;
//
//    @NotBlank
//    @Column(name = "description")
//    private String description;
//
//    @Unsigned
//    @Column(name = "value")
//    private Double value;
//
//
//    @Temporal(TemporalType.DATE) // Specify that only the date part should be stored
//    // Specify that only the date part should be stored
//    @Column(name = "start_date")
//    @Convert(converter = DateConverter.class) // Use a custom converter
//    private Date startDate;
//
//
//    @Temporal(TemporalType.DATE) // Specify that only the date part should be stored
//    @Column(name = "end_date")
//    @Convert(converter = DateConverter.class) // Use a custom converter
//    private Date endDate;
//
//    @Column(name = "status")
//    private Boolean status;
//
//
//    @OneToMany(mappedBy = "promotion")
//    private List<OrderEntity> orders = new ArrayList<>();
//
//    @Column(name="quanity")
//    private int quantity;
//}
