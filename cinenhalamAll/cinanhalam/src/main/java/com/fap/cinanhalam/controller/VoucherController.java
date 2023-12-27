package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.VoucherDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class VoucherController {

  @Autowired
  private VoucherService voucherService;

  @GetMapping(value = "/voucher")
  public ListOutput showVouchersTrue() {
    ListOutput result = new ListOutput();
    result.setListResult(voucherService.findAllWithStatusIsTrue());
    return result;
  }

  @GetMapping(value = "/staff/voucher")
  public ListOutput showVouchers() {
    ListOutput result = new ListOutput();
    result.setListResult(voucherService.findAll());
    return result;
  }

  @PostMapping(value = "/staff/voucher")
  public VoucherDTO createVoucher(@RequestBody VoucherDTO model) {
    return voucherService.save(model);
  }

  @PutMapping(value = "/staff/voucher/{id}")
  public VoucherDTO updateVoucher(@RequestBody VoucherDTO model, @PathVariable("id") long id) {
    model.setId(id);
    return voucherService.save(model);
  }

  @DeleteMapping(value = "/staff/voucher/{id}")
  public void changeVoucherStatus(@PathVariable("id") Long id) {
    voucherService.changeStatus(id);
  }
}
