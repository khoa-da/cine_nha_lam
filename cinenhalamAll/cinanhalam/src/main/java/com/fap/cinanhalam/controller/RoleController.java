package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.RoleDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping(value="admin/role")
    public ListOutput showRoleForAdmin() {
        ListOutput result = new ListOutput();
        result.setListResult(roleService.findAll());
        return  result;
    }

    @PostMapping(value= "admin/role")
    public RoleDTO createRole(@RequestBody RoleDTO model){
        return (RoleDTO) roleService.save(model);
    }

    @PutMapping(value = "admin/role/{id}")
    public RoleDTO updateRole(@RequestBody RoleDTO model, @PathVariable("id") long id){
        model.setId(id);
        return (RoleDTO) roleService.save(model);
    }

    @DeleteMapping(value = "admin/role/{id}")
    public void changeStatus(@RequestBody @PathVariable("id") Long id) {roleService.changeStatus(id);}
}
