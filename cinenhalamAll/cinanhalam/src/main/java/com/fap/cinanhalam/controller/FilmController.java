package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.FilmDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.FilmCategoryService;
import com.fap.cinanhalam.service.impl.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class FilmController {

    @Autowired
    FilmService filmService;

    @Autowired
    FilmCategoryService filmCategoryService;

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

    @GetMapping(value="film/top4")
    public ListOutput listTop4Film() {
        ListOutput result = new ListOutput();
        result.setListResult(filmService.findTop4FilmBaseOnOrderDetail());
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


    @GetMapping(value = "customer/film/{filmId}")
    public ListOutput showOneFilmForCustomer(@PathVariable Long filmId) {
        ListOutput result = new ListOutput();
        List<FilmDTO> films = filmService.findOneWithStatusIsTrue(filmId);
        result.setListResult(films);
        return result;
    }

    @GetMapping(value = "customer/film/category/{categoryName}")
    public ListOutput findAllByCategoryNamesByFilmId(@PathVariable String categoryName) {
        ListOutput result = new ListOutput();
        String categoryNameWithoutSpace = categoryName.replaceAll("\\s", "");
        List<FilmDTO> films = filmCategoryService.findAllByCategoryName(categoryNameWithoutSpace);
        result.setListResult(films);
        return result;
    }


}