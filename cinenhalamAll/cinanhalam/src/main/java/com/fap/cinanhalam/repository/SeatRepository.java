package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.RoomEntity;
import com.fap.cinanhalam.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

    SeatEntity findOneById(long id);

    List<SeatEntity> findAllByStatusTrue();
}
