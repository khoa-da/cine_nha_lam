package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.SeatEntity;
import com.fap.cinanhalam.entity.SliderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SliderRepository extends JpaRepository<SliderEntity, Long> {
    SliderEntity findOneById(long id);

    List<SliderEntity> findAllByStatusTrue();
}
