package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.CategoryEntity;
import com.fap.cinanhalam.entity.FilmEntity;
import com.fap.cinanhalam.entity.ProvinceEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<FilmEntity, Long> {

    @Query("SELECT f FROM FilmEntity f, ScheduleEntity s, TicketEntity t, TicketDetailEntity td " +
        "WHERE f.id = s.film.id " +
        "AND s.id = t.schedule.id " +
        "AND t.id = td.ticket.id " +
        "GROUP BY f.id " +
        "ORDER BY COUNT(td.id) DESC LIMIT 4")
    List<FilmEntity> findTop4FilmsWithMostTicketDetails();


    FilmEntity findOneById(long id);

    List<FilmEntity> findAllByStatusTrue();

    @Query("SELECT f FROM FilmEntity f WHERE f.name = :name and f.status = true")
    FilmEntity findOneByNameAndStatusTrue(@Param("name") String name);

    @Query("SELECT f FROM FilmEntity f WHERE f.id = :id AND f.status = true")
    FilmEntity findOneByIdAndStatusTrue(@Param("id") Long id);

    @Query("SELECT f FROM FilmEntity f WHERE f.status = true AND f.filmStatus Like '%Showing%'")
    List<FilmEntity> findAllFilmShowing();

    @Query("SELECT f FROM FilmEntity f WHERE f.status = true AND f.filmStatus Like '%Coming%'")
    List<FilmEntity> findAllFilmComing();

}
