package com.fap.cinanhalam.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FAQsDTO extends BaseDTO{
    private String title;

    private String content;
}
