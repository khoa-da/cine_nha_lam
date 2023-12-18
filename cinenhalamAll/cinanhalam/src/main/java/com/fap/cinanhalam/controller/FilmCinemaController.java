package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.FilmCinemaDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.FilmCinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class FilmCinemaController {

    @Autowired
    FilmCinemaService filmCinemaService;

    @GetMapping(value="film-cinema")
    public ListOutput showFilmCinemas() {
        ListOutput result = new ListOutput();
        result.setListResult(filmCinemaService.findAllWithStatusIsTrue());
        return  result;
    }

    @GetMapping(value="staff/film-cinema")
    public ListOutput showFilmCinemasForStaff() {
        ListOutput result = new ListOutput();
        result.setListResult(filmCinemaService.findAll());
        return  result;
    }

    @PostMapping(value="staff/film-cinema")
    public FilmCinemaDTO createFilmCinema(@RequestBody FilmCinemaDTO model){
        return (FilmCinemaDTO) filmCinemaService.save(model);
    }

    @PutMapping(value="staff/film-cinema/{id}")
    public FilmCinemaDTO updateFilmCinema(@RequestBody FilmCinemaDTO model,@PathVariable("id") long id){
        model.setId(id);
        return (FilmCinemaDTO) filmCinemaService.save(model);
    }

    @DeleteMapping(value = "staff/film-cinema/{id}")
    public void changeStatus(@RequestBody @PathVariable("id") Long id){
        filmCinemaService.changeStatus(id);
    }

    @GetMapping(value="customer/film-cinema/{provinceName}")
    public ListOutput showAllCinemaByProvinceName(@RequestParam String provinceName) {
        ListOutput result = new ListOutput();
        result.setListResult(filmCinemaService.findAllCinemaByProvinceName(provinceName));
        return result;
    }
}