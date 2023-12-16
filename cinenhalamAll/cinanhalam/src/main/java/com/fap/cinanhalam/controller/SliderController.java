package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.SliderDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class SliderController {

    @Autowired
    SliderService sliderService;

    @GetMapping(value = "admin/slider")
    public ListOutput showSliders() {
        ListOutput result = new ListOutput();
        result.setListResult(sliderService.findAll());
        return result;
    }

    @GetMapping(value = "admin/slider/active")
    public ListOutput showActiveSliders() {
        ListOutput result = new ListOutput();
        result.setListResult(sliderService.findAllWithStatusIsTrue());
        return result;
    }

    @PostMapping(value = "admin/slider")
    public SliderDTO createSlider(@RequestBody SliderDTO model) {
        return sliderService.save(model);
    }

    @PutMapping(value = "admin/slider/{id}")
    public SliderDTO updateSlider(@RequestBody SliderDTO model, @PathVariable("id") long id) {
        model.setId(id);
        return sliderService.save(model);
    }

    @DeleteMapping(value = "admin/slider/{id}")
    public void changeSliderStatus(@PathVariable("id") Long id) {
        sliderService.changeStatus(id);
    }
}
