package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.ScheduleDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @GetMapping(value="schedule")
    public ListOutput showSchedules() {
        ListOutput result = new ListOutput();
        result.setListResult(scheduleService.findAllWithStatusIsTrue());
        return  result;
    }

    @GetMapping(value="staff/schedule")
    public ListOutput showSchedulesForStaff() {
        ListOutput result = new ListOutput();
        result.setListResult(scheduleService.findAll());
        return  result;
    }

    @PostMapping(value="staff/schedule")
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO model){
        return (ScheduleDTO) scheduleService.save(model);
    }

    @PutMapping(value="staff/schedule/{id}")
    public ScheduleDTO updateSchedule(@RequestBody ScheduleDTO model,@PathVariable("id") long id){
        model.setId(id);
        return (ScheduleDTO) scheduleService.save(model);
    }

    @DeleteMapping(value = "staff/schedule/{id}")
    public void changeStatus(@RequestBody @PathVariable("id") Long id){
        scheduleService.changeStatus(id);
    }

}