package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.UserRoleDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

    @GetMapping(value = "admin/userRole")
    public ListOutput showUserRoles() {
        ListOutput result = new ListOutput();
        result.setListResult(userRoleService.findAll());
        return result;
    }

    @GetMapping(value = "admin/userRole/active")
    public ListOutput showActiveUserRoles() {
        ListOutput result = new ListOutput();
        result.setListResult(userRoleService.findAllWithStatusIsTrue());
        return result;
    }

    @PostMapping(value = "admin/userRole")
    public UserRoleDTO createUserRole(@RequestBody UserRoleDTO model) {
        return userRoleService.save(model);
    }

    @PutMapping(value = "admin/userRole/{id}")
    public UserRoleDTO updateUserRole(@RequestBody UserRoleDTO model, @PathVariable("id") long id) {
        model.setId(id);
        return userRoleService.save(model);
    }

    @DeleteMapping(value = "admin/userRole/{id}")
    public void changeUserRoleStatus(@PathVariable("id") Long id) {
        userRoleService.changeStatus(id);
    }
}
