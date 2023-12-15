package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.OrderDetailDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class OrderDetailController {

  @Autowired
  private OrderDetailService orderDetailService;

  @GetMapping(value = "/order-detail")
  public ListOutput showOrderDetails() {
    ListOutput result = new ListOutput();
    result.setListResult(orderDetailService.findAllWithStatusIsTrue());
    return result;
  }

  @GetMapping(value = "/order-detail/{id}")
  public OrderDetailDTO showOrderDetailById(@PathVariable("id") Long id) {
    return orderDetailService.findById(id);
  }

  @GetMapping(value = "/staff/order-detail")
  public ListOutput showOrderDetailsForStaff() {
    ListOutput result = new ListOutput();
    result.setListResult(orderDetailService.findAll());
    return result;
  }


  @PostMapping(value = "/staff/order-detail")
  public OrderDetailDTO createOrderDetail(@RequestBody OrderDetailDTO model) {
    return orderDetailService.save(model);
  }

  @PutMapping(value = "/staff/order-detail/{id}")
  public OrderDetailDTO updateOrderDetail(@RequestBody OrderDetailDTO model, @PathVariable("id") long id) {
    model.setId(id);
    return orderDetailService.save(model);
  }

  @DeleteMapping(value = "/staff/order-detail/{id}")
  public void changeStatusOrderDetail(@PathVariable("id") Long id) {
    orderDetailService.changeStatus(id);
  }
}
