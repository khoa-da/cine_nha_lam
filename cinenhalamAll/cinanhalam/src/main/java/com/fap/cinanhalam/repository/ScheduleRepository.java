package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.ProvinceEntity;
import com.fap.cinanhalam.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    ScheduleEntity findOneById(long id);

    List<ScheduleEntity> findAllByStatusTrue();

    @Query("SELECT s FROM ScheduleEntity s WHERE s.id = :id AND s.status = true")
    ScheduleEntity findOneByIdAndStatusTrue(@Param("id") Long id);
}
