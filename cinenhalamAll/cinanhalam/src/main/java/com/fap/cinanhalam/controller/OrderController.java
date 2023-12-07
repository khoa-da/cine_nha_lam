package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.OrderDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @GetMapping(value = "/order")
  public ListOutput showOrders() {
    ListOutput result = new ListOutput();
    result.setListResult(orderService.findAllWithStatusIsTrue());
    return result;
  }

  @PostMapping(value = "/staff/order")
  public OrderDTO createOrder(@RequestBody OrderDTO model) {
    return orderService.save(model);
  }

  @PutMapping(value = "/staff/order/{id}")
  public OrderDTO updateOrder(@RequestBody OrderDTO model, @PathVariable("id") long id) {
    model.setId(id);
    return orderService.save(model);
  }

  @DeleteMapping(value = "/staff/order/{id}")
  public void changeOrderStatus(@PathVariable("id") Long id) {
    orderService.changeStatus(id);
  }
}
