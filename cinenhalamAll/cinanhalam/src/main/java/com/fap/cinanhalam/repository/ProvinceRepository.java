package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.PostEntity;
import com.fap.cinanhalam.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<ProvinceEntity, Long>{
    ProvinceEntity findOneById(long id);

    List<ProvinceEntity> findAllByStatusTrue();

    @Query("SELECT p FROM ProvinceEntity p WHERE p.name = :name AND p.status = true")
    ProvinceEntity findOneByNameAndStatusTrue(@Param("name") String name);

//    @Query("SELECT DISTINCT p " +
//            "FROM CinemaEntity c " +
//            "JOIN FilmCinemaEntity fc ON fc.cinema.id = c.id " +
//            "JOIN FilmEntity f ON f.id = fc.film.id " +
//            "JOIN ProvinceEntity p ON p.id = c.province.id " +
//            "WHERE f.id = :filmId AND c.status = true AND f.status = true AND p.status = true")
//    List<ProvinceEntity> findProvincesByFilmId(@Param("filmId") Long filmId);
}
