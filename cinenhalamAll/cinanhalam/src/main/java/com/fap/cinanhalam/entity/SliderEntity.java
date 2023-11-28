package com.fap.cinanhalam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
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
@Table(name = "slider")
public class SliderEntity extends BaseEntity{
    @NotBlank
    @Size(min =3 ,max=150)
    @Column(name = "slider_name")
    private String sliderName;

    @NotBlank
    @Size(min = 20, max=500)
    @Column(name = "slider_description")
    private String sliderDescription;

    @NotBlank
    @Lob
    @Column(name = "slider_imageURL")
    private String sliderImageURL;

    @Column(name = "status")
    private Boolean status;
}
