package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.TicketDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class TicketController {

  @Autowired
  private TicketService ticketService;

  @GetMapping(value = "/ticket")
  public ListOutput showTickets() {
    ListOutput result = new ListOutput();
    result.setListResult(ticketService.findAllWithStatusIsTrue());
    return result;
  }

  @PostMapping(value = "/staff/ticket")
  public TicketDTO createTicket(@RequestBody TicketDTO model) {
    return ticketService.save(model);
  }

  @PutMapping(value = "/staff/ticket/{id}")
  public TicketDTO updateTicket(@RequestBody TicketDTO model, @PathVariable("id") long id) {
    model.setId(id);
    return ticketService.save(model);
  }

  @DeleteMapping(value = "/staff/ticket/{id}")
  public void changeTicketStatus(@PathVariable("id") Long id) {
    ticketService.changeStatus(id);
  }
}
