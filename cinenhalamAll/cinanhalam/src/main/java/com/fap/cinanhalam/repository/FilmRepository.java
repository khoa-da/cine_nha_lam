package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.CategoryEntity;
import com.fap.cinanhalam.entity.FilmEntity;
import com.fap.cinanhalam.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<FilmEntity, Long> {

    FilmEntity findOneById(long id);

    List<FilmEntity> findAllByStatusTrue();

    @Query("SELECT f FROM FilmEntity f WHERE f.name = :name and f.status = true")
    FilmEntity findOneByNameAndStatusTrue(@Param("name") String name);

    @Query("SELECT f FROM FilmEntity f WHERE f.id = :id AND f.status = true")
    FilmEntity findOneByIdAndStatusTrue(@Param("id") Long id);

}
