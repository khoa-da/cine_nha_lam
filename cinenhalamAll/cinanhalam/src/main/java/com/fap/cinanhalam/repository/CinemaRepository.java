package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.dto.CinemaDTO;
import com.fap.cinanhalam.entity.CategoryEntity;
import com.fap.cinanhalam.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {

    CinemaEntity findOneById(long id);

    List<CinemaEntity> findAllByStatusTrue();
}
