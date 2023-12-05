package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.FilmEntity;
import com.fap.cinanhalam.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<FilmEntity, Long> {

    FilmEntity findOneById(long id);

    List<FilmEntity> findAllByStatusTrue();
}
