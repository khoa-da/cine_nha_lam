package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.PostDTO;
import com.fap.cinanhalam.dto.UserDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.repository.UserRepository;
import com.fap.cinanhalam.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "admin/user")
    public ListOutput showUsers(){
        ListOutput result = new ListOutput();
        result.setListResult(userService.findAll());
        return result;
    }

    @PostMapping(value="admin/user")
    public UserDTO createUser(@RequestBody UserDTO model){
        return (UserDTO) userService.save(model);
    }

    @PutMapping(value="admin/user/{id}")
    public UserDTO updateUser(@RequestBody UserDTO model, @PathVariable("id") long id){
        model.setId(id);
        return (UserDTO) userService.save(model);
    }

    @DeleteMapping(value="admin/user/{id}")
    public void changeStatus(@RequestBody @PathVariable("id") Long id){
        userService.changeStatus(id);
    }
}
