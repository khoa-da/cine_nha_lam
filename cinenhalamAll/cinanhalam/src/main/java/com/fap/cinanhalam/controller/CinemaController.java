package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.CinemaDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class CinemaController {

    @Autowired
    CinemaService cinemaService;

    @GetMapping(value="cinema")
    public ListOutput showCinemas() {
        ListOutput result = new ListOutput();
        result.setListResult(cinemaService.findAllWithStatusIsTrue());
        return  result;
    }

    @GetMapping(value="staff/cinema")
    public ListOutput showCinemasForStaff() {
        ListOutput result = new ListOutput();
        result.setListResult(cinemaService.findAll());
        return  result;
    }

    @PostMapping(value="staff/cinema")
    public CinemaDTO createCinema(@RequestBody CinemaDTO model){
        return (CinemaDTO) cinemaService.save(model);
    }

    @PutMapping(value="staff/cinema/{id}")
    public CinemaDTO updateCinema(@RequestBody CinemaDTO model,@PathVariable("id") long id){
        model.setId(id);
        return (CinemaDTO) cinemaService.save(model);
    }

    @DeleteMapping(value = "staff/cinema/{id}")
    public void changeStatus(@RequestBody @PathVariable("id") Long id){
        cinemaService.changeStatus(id);
    }

}