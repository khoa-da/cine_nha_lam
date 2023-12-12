package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.dto.FeedbackDTO;
import com.fap.cinanhalam.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
    FeedbackEntity findOneById(long id);

    List<FeedbackEntity> findAllByStatusTrue();
}
