package com.fap.cinanhalam.entity;

import com.fap.cinanhalam.converter.DateConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jdk.jfr.Unsigned;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "feedback")
public class FeedbackEntity extends BaseEntity{
    @NotBlank
    @Size(min = 0, max = 150)
    @Column(name = "content")
    private String content;

    @Unsigned
    @Column(name = "rating")
    private Integer rating;

    @Column(name = "status")
    private Boolean status;

    @Unsigned
    @Column(name = "belong_to")
    private String belongTo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "replyer_id")
    private UserEntity replyer;


    @Size(min = 0, max = 150)
    @Column(name = "reply_content")
    private String replyContent;


    @Temporal(TemporalType.DATE)
    @Column(name = "reply_date")
    @CreationTimestamp
    @Convert(converter = DateConverter.class) // Use a custom converter
    private Date replyDate;
}
