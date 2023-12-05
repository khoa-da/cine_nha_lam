package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.FilmCategoryDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.FilmCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class FilmCategoryController {

    @Autowired
    FilmCategoryService filmCategoryService;

    @GetMapping(value="film-category")
    public ListOutput showFilmCategories() {
        ListOutput result = new ListOutput();
        result.setListResult(filmCategoryService.findAllWithStatusIsTrue());
        return result;
    }

    @GetMapping(value="staff/film-category")
    public ListOutput showFilmCategoriesForStaff() {
        ListOutput result = new ListOutput();
        result.setListResult(filmCategoryService.findAll());
        return result;
    }

    @PostMapping(value="staff/film-category")
    public FilmCategoryDTO createFilmCategory(@RequestBody FilmCategoryDTO model){
        return (FilmCategoryDTO) filmCategoryService.save(model);
    }

    @PutMapping(value="staff/film-category/{id}")
    public FilmCategoryDTO updateFilmCategory(@RequestBody FilmCategoryDTO model,@PathVariable("id") long id){
        model.setId(id);
        return (FilmCategoryDTO) filmCategoryService.save(model);
    }

    @DeleteMapping(value = "staff/film-category/{id}")
    public void changeStatus(@RequestBody @PathVariable("id") Long id){
        filmCategoryService.changeStatus(id);
    }

}