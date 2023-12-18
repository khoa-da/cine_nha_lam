package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.ProvinceDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class ProvinceController {

    @Autowired
    ProvinceService provinceService;

    @GetMapping(value="province")
    public ListOutput showProvinces() {
        ListOutput result = new ListOutput();
        result.setListResult(provinceService.findAllWithStatusIsTrue());
        return  result;
    }

    @GetMapping(value="staff/province")
    public ListOutput showProvincesForStaff() {
        ListOutput result = new ListOutput();
        result.setListResult(provinceService.findAll());
        return  result;
    }

    @PostMapping(value="staff/province")
    public ProvinceDTO createProvince(@RequestBody ProvinceDTO model){
        return (ProvinceDTO) provinceService.save(model);
    }

    @PutMapping(value="staff/province/{id}")
    public ProvinceDTO updateProvince(@RequestBody ProvinceDTO model,@PathVariable("id") long id){
        model.setId(id);
        return (ProvinceDTO) provinceService.save(model);
    }

    @DeleteMapping(value = "staff/province/{id}")
    public void changeStatus(@RequestBody @PathVariable("id") Long id){
        provinceService.changeStatus(id);
    }

//    @GetMapping("customer/province/{filmId}")
//    public List<ProvinceDTO> getProvincesByFilmId(@PathVariable Long filmId) {
//        return provinceService.findProvincesByFilmId(filmId);
//    }
}