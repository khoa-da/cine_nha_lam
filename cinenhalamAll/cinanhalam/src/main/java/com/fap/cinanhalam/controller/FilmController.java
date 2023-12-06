package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.FilmDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class FilmController {

    @Autowired
    FilmService filmService;

    @GetMapping(value="film")
    public ListOutput showFilms() {
        ListOutput result = new ListOutput();
        result.setListResult(filmService.findAllWithStatusIsTrue());
        return  result;
    }

    @GetMapping(value="staff/film")
    public ListOutput showFilmsForStaff() {
        ListOutput result = new ListOutput();
        result.setListResult(filmService.findAll());
        return  result;
    }

    @PostMapping(value="staff/film")
    public FilmDTO createFilm(@RequestBody FilmDTO model){
        return (FilmDTO) filmService.save(model);
    }

    @PutMapping(value="staff/film/{id}")
    public FilmDTO updateFilm(@RequestBody FilmDTO model,@PathVariable("id") long id){
        model.setId(id);
        return (FilmDTO) filmService.save(model);
    }

    @DeleteMapping(value = "staff/film/{id}")
    public void changeStatus(@RequestBody @PathVariable("id") Long id){
        filmService.changeStatus(id);
    }

}