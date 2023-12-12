package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.TicketDetailDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.TicketDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class TicketDetailController {

  @Autowired
  private TicketDetailService TicketDetailService;

  @GetMapping(value = "/ticket-detail")
  public ListOutput showTicketDetails() {
    ListOutput result = new ListOutput();
    result.setListResult(TicketDetailService.findAllWithStatusIsTrue());
    return result;
  }



  @PostMapping(value = "/staff/ticket-detail")
  public TicketDetailDTO createTicketDetail(@RequestBody TicketDetailDTO model) {
    return TicketDetailService.save(model);
  }

  @PutMapping(value = "/staff/ticket-detail/{id}")
  public TicketDetailDTO updateTicketDetail(@RequestBody TicketDetailDTO model, @PathVariable("id") long id) {
    model.setId(id);
    return TicketDetailService.save(model);
  }

  @DeleteMapping(value = "/staff/ticket-detail/{id}")
  public void changeTicketDetailStatus(@PathVariable("id") Long id) {
    TicketDetailService.changeStatus(id);
  }
}
