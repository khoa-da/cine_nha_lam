package com.fap.cinanhalam.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CinemaDTO extends BaseDTO{

    private String name; // Tên của rạp chiếu phim

    private String location; // Địa điểm của rạp chiếu phim

    private int capacity; // Sức chứa tổng cộng của rạp chiếu phim

    private String contactNumber; // Số điện thoại liên hệ của rạp

    private String website; // Địa chỉ website của rạp

    private String openingHours; // Giờ mở cửa của rạp
}
