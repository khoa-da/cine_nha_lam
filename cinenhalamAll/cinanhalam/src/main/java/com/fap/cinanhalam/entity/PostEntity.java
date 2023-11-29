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
@Table(name = "post")
public class PostEntity extends BaseEntity{

    @NotBlank
    @Size(min = 3, max = 500)
    @Column(name = "title")
    private String title;

    @NotBlank
    @Size(min = 10, max = 2000)
    @Column(name = "content")
    private String content;

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "description")
    private String description;

    @NotBlank
    @Lob
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

}
