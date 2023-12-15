package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.CinemaEntity;
import com.fap.cinanhalam.entity.FilmCategoryEntity;
import com.fap.cinanhalam.entity.FilmEntity;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmCategoryRepository extends JpaRepository<FilmCategoryEntity, Long> {

    FilmCategoryEntity findOneById(long id);

    List<FilmCategoryEntity> findAllByStatusTrue();

    @Query("SELECT fc.category.name FROM FilmCategoryEntity fc WHERE fc.film.id = :filmId and fc.status = true")
    List<String> findCategoryNamesByFilmId(@Param("filmId") Long filmId);

    @Query("SELECT fc.film FROM FilmCategoryEntity fc WHERE fc.category.name LIKE %:categoryName% AND fc.status = true")
    List<FilmEntity> findAllFilmByCategoryName(@Param("categoryName") String categoryName);

}
