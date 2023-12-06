package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.CinemaEntity;
import com.fap.cinanhalam.entity.FilmCategoryEntity;
import com.fap.cinanhalam.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmCategoryRepository extends JpaRepository<FilmCategoryEntity, Long> {

    FilmCategoryEntity findOneById(long id);

    List<FilmCategoryEntity> findAllByStatusTrue();
}
