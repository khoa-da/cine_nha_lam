package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.SeatDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class SeatController {

    @Autowired
    SeatService seatService;

    @GetMapping(value = "admin/seat")
    public ListOutput showSeats() {
        ListOutput result = new ListOutput();
        result.setListResult(seatService.findAll());
        return result;
    }

    @GetMapping(value = "admin/seat/active")
    public ListOutput showActiveSeats() {
        ListOutput result = new ListOutput();
        result.setListResult(seatService.findAllWithStatusIsTrue());
        return result;
    }

    @PostMapping(value = "admin/seat")
    public SeatDTO createSeat(@RequestBody SeatDTO model) {
        return seatService.save(model);
    }

    @PutMapping(value = "admin/seat/{id}")
    public SeatDTO updateSeat(@RequestBody SeatDTO model, @PathVariable("id") long id) {
        model.setId(id);
        return seatService.save(model);
    }

    @DeleteMapping(value = "admin/seat/{id}")
    public void changeSeatStatus(@PathVariable("id") Long id) {
        seatService.changeStatus(id);
    }
}
