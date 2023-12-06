package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.FilmCategoryEntity;
import com.fap.cinanhalam.entity.FilmCinemaEntity;
import com.fap.cinanhalam.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmCinemaRepository extends JpaRepository<FilmCinemaEntity, Long> {

    FilmCinemaEntity findOneById(long id);

    List<FilmCinemaEntity> findAllByStatusTrue();
}
