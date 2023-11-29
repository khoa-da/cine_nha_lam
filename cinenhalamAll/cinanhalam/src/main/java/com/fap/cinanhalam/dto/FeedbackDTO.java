package com.fap.cinanhalam.dto;

import lombok.*;

import java.util.Date;
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeedbackDTO extends BaseDTO{
    private String content;
    private Integer rating;
    private String belongTo;
    private String replyContent;
    private Date replyDate;

}
