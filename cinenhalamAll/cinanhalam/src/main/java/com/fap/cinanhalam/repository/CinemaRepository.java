package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.dto.CinemaDTO;
import com.fap.cinanhalam.entity.CategoryEntity;
import com.fap.cinanhalam.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {

    CinemaEntity findOneById(long id);

    List<CinemaEntity> findAllByStatusTrue();

    @Query("SELECT c FROM CinemaEntity c WHERE c.id = :id and c.status = true")
    CinemaEntity findOneByIdAndStatusTrue(@Param("id") Long id);

    @Query("SELECT c FROM CinemaEntity c JOIN c.province p WHERE p.name = :provinceName and c.status = true")
    List<CinemaEntity> findAllByProvinceName(@Param("provinceName") String provinceName);
}
