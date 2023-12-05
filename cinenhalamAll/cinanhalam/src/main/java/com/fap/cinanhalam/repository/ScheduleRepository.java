package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.ProvinceEntity;
import com.fap.cinanhalam.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    ScheduleEntity findOneById(long id);

    List<ScheduleEntity> findAllByStatusTrue();
}
