package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.dto.CinemaDTO;
import com.fap.cinanhalam.entity.CategoryEntity;
import com.fap.cinanhalam.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {

    CinemaEntity findOneById(long id);

    List<CinemaEntity> findAllByStatusTrue();

    @Query("SELECT c FROM CinemaEntity c WHERE c.id = :id and c.status = true")
    CinemaEntity findOneByIdAndStatusTrue(@Param("id") Long id);

    @Query("SELECT c FROM CinemaEntity c JOIN c.province p WHERE p.name = :provinceName and c.status = true")
    List<CinemaEntity> findAllByProvinceName(@Param("provinceName") String provinceName);

    @Query("SELECT DISTINCT c.name, s.startHour, s.endHour " +
            "FROM CinemaEntity c " +
            "JOIN ProvinceEntity p ON p.id = c.province.id " +
            "JOIN FilmCinemaEntity fc ON fc.cinema.id = c.id " +
            "JOIN ScheduleEntity s ON s.id = fc.schedule.id " +
            "WHERE s.film.id = :filmId AND p.name = :provinceName AND s.screeningDate = :date")
    List<Object[]> findDistinctCinemaDetailsByFilmIdAndProvinceName(@Param("filmId") Long filmId,
                                                                    @Param("provinceName") String provinceName,
                                                                    @Param("date") Date date);
}
