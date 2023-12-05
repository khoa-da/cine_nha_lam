package com.fap.cinanhalam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "cinema")
public class CinemaEntity extends BaseEntity{

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "name",unique = true)
    private String name; // Tên của rạp chiếu phim

    @Column(name = "location")
    private String location; // Địa điểm của rạp chiếu phim

    @Column(name = "capacity")
    private int capacity; // Sức chứa tổng cộng của rạp chiếu phim

    @Column(name = "contact_number")
    private String contactNumber; // Số điện thoại liên hệ của rạp

    @Column(name = "website")
    private String website; // Địa chỉ website của rạp

    @Column(name = "opening_hours")
    private String openingHours; // Giờ mở cửa của rạp

    @OneToMany(mappedBy = "cinema")
    private List<FilmCinemaEntity> filmCinemas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false)
    private ProvinceEntity province;
}
