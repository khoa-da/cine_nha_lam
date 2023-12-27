package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.VoucherUsageDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.VoucherUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class VoucherUsageController {

  @Autowired
  private VoucherUsageService voucherUsageService;

  @GetMapping(value = "/voucher-usage")
  public ListOutput showVoucherUsages() {
    ListOutput result = new ListOutput();
    result.setListResult(voucherUsageService.findAllWithStatusIsTrue());
    return result;
  }

  @PostMapping(value = "/staff/voucher-usage")
  public VoucherUsageDTO createVoucherUsage(@RequestBody VoucherUsageDTO model) {
    return voucherUsageService.save(model);
  }

  @PutMapping(value = "/staff/voucher-usage/{id}")
  public VoucherUsageDTO updateVoucherUsage(@RequestBody VoucherUsageDTO model, @PathVariable("id") long id) {
    model.setId(id);
    return voucherUsageService.save(model);
  }

  @DeleteMapping(value = "/staff/voucher-usage/{id}")
  public void changeVoucherUsageStatus(@PathVariable("id") Long id) {
    voucherUsageService.changeStatus(id);
  }

  @DeleteMapping(value = "/staff/voucher-usage/delete/{id}")
  public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
    try {
      voucherUsageService.deleteById(id);
      return new ResponseEntity<>("Deletion successful", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Deletion failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
