package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.PostEntity;
import com.fap.cinanhalam.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ProvinceRepository extends JpaRepository<ProvinceEntity, Long>{
    ProvinceEntity findOneById(long id);

    List<ProvinceEntity> findAllByStatusTrue();

    @Query("SELECT p FROM ProvinceEntity p WHERE p.name = :name AND p.status = true")
    ProvinceEntity findOneByNameAndStatusTrue(@Param("name") String name);

    @Query("SELECT DISTINCT p.name " +
            "FROM ProvinceEntity p " +
            "JOIN CinemaEntity c ON c.province.id = p.id " +
            "JOIN FilmCinemaEntity fc ON fc.cinema.id = c.id " +
            "JOIN ScheduleEntity s ON s.id = fc.schedule.id " +
            "WHERE s.film.id = :filmId AND s.screeningDate = :screeningDate")
    List<String> findDistinctProvinceNamesByFilmIdAndScreeningDate(
            @Param("filmId") Long filmId,
            @Param("screeningDate") Date screeningDate);


}
