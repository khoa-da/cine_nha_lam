package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.RoomDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping(value = "admin/room")
    public ListOutput showRooms() {
        ListOutput result = new ListOutput();
        result.setListResult(roomService.findAll());
        return result;
    }

    @GetMapping(value = "room")
    public ListOutput showRoomsForUser() {
        ListOutput result = new ListOutput();
        result.setListResult(roomService.findAllWithStatusIsTrue());
        return result;
    }

    @PostMapping(value = "admin/room")
    public RoomDTO createRoom(@RequestBody RoomDTO model) {
        return roomService.save(model);
    }

    @PutMapping(value = "admin/room/{id}")
    public RoomDTO updateRoom(@RequestBody RoomDTO model, @PathVariable("id") long id) {
        model.setId(id);
        return roomService.save(model);
    }

    @DeleteMapping(value = "admin/room/{id}")
    public void changeRoomStatus(@PathVariable("id") Long id) {
        roomService.changeStatus(id);
    }
}
