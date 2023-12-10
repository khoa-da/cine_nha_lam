package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.FoodOrderDetailDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.FoodOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class FoodOrderDetailController {

  @Autowired
  private FoodOrderDetailService foodOrderDetailService;

  @GetMapping("/food-order-detail")
  public ListOutput showFoodOrderDetails() {
    ListOutput result = new ListOutput();
    result.setListResult(foodOrderDetailService.findAll());
    return result;
  }

  @GetMapping("/staff/food-order-detail")
  public ListOutput showActiveFoodOrderDetails() {
    ListOutput result = new ListOutput();
    result.setListResult(foodOrderDetailService.findAllWithStatusIsTrue());
    return result;
  }

  @PostMapping("/staff/food-order-detail")
  public FoodOrderDetailDTO createFoodOrderDetail(@RequestBody FoodOrderDetailDTO model) {
    return foodOrderDetailService.save(model);
  }

  @PutMapping("/staff/food-order-detail/{id}")
  public FoodOrderDetailDTO updateFoodOrderDetail(@RequestBody FoodOrderDetailDTO model, @PathVariable("id") long id) {
    model.setId(id);
    return foodOrderDetailService.save(model);
  }

  @DeleteMapping("/staff/food-order-detail/{id}")
  public void deleteFoodOrderDetail(@PathVariable("id") Long id) {
    foodOrderDetailService.changeStatus(id);
  }
}
