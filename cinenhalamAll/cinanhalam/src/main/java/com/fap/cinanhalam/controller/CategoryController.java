package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.CategoryDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(value="category")
    public ListOutput showCategories() {
        ListOutput result = new ListOutput();
        result.setListResult(categoryService.findAllWithStatusIsTrue());
        return result;
    }

    @GetMapping(value="staff/category")
    public ListOutput showCategoriesForStaff() {
        ListOutput result = new ListOutput();
        result.setListResult(categoryService.findAll());
        return result;
    }

    @PostMapping(value="staff/category")
    public CategoryDTO createCategory(@RequestBody CategoryDTO model){
        return (CategoryDTO) categoryService.save(model);
    }

    @PutMapping(value="staff/category/{id}")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO model,@PathVariable("id") long id){
        model.setId(id);
        return (CategoryDTO) categoryService.save(model);
    }

    @DeleteMapping(value = "staff/category/{id}")
    public void changeStatus(@RequestBody @PathVariable("id") Long id){
        categoryService.changeStatus(id);
    }

}