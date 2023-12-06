package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.FoodDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class FoodController {

  @Autowired
  private FoodService foodService;

  @GetMapping(value = "/food")
  public ListOutput showFoods() {
    ListOutput result = new ListOutput();
    result.setListResult(foodService.findAllWithStatusIsTrue());
    return result;
  }

  @GetMapping(value = "/staff/food")
  public ListOutput showFoodsForStaff() {
    ListOutput result = new ListOutput();
    result.setListResult(foodService.findAll());
    return result;
  }

  @PostMapping(value = "/staff/food")
  public FoodDTO createFood(@RequestBody FoodDTO model) {
    return foodService.save(model);
  }

  @PutMapping(value = "/staff/food/{id}")
  public FoodDTO updateFood(@RequestBody FoodDTO model, @PathVariable("id") long id) {
    model.setId(id);
    return foodService.save(model);
  }

  @DeleteMapping(value = "/staff/food/{id}")
  public void changeFood(@PathVariable("id") Long id) {
    foodService.changeStatus(id);
  }
}
