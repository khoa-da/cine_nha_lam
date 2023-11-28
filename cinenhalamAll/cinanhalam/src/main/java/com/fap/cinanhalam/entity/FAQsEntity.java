package com.fap.cinanhalam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="FAQs")
public class FAQsEntity extends BaseEntity{

    @NotBlank
    @Size(min = 3, max = 300)
    @Column(name = "title")
    private String title;

    @NotBlank
    @Size(min = 2, max = 1000)
    @Column(name = "content")
    private String content;


    @Column(name = "status")
    private Boolean status;
}
