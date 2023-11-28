package com.fap.cinanhalam.entity;

import com.fap.cinanhalam.converter.DateConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable  = false)
    private Long id;

    @Temporal(TemporalType.DATE) // Specify that only the date part should be stored
    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    @Convert(converter = DateConverter.class) // Use a custom converter
    private Date createdDate;
}
