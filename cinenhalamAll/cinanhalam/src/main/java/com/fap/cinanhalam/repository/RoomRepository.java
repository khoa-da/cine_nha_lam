package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.RoomEntity;
import com.fap.cinanhalam.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    RoomEntity findOneById(long id);

    List<RoomEntity> findAllByStatusTrue();
}
