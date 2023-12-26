package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.VoucherUsageDTO;
import com.fap.cinanhalam.entity.FoodOrderDetailEntity;
import com.fap.cinanhalam.entity.OrderDetailEntity;
import com.fap.cinanhalam.entity.OrderEntity;
import com.fap.cinanhalam.entity.TicketDetailEntity;
import com.fap.cinanhalam.entity.TicketEntity;
import com.fap.cinanhalam.entity.VoucherEntity;
import com.fap.cinanhalam.entity.VoucherUsageEntity;
import com.fap.cinanhalam.repository.FoodOrderDetailRepository;
import com.fap.cinanhalam.repository.OrderDetailRepository;
import com.fap.cinanhalam.repository.OrderRepository;
import com.fap.cinanhalam.repository.TicketDetailRepository;
import com.fap.cinanhalam.repository.TicketRepository;
import com.fap.cinanhalam.repository.VoucherRepository;
import com.fap.cinanhalam.repository.VoucherUsageRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoucherUsageService implements IGenericService<VoucherUsageDTO> {

  @Autowired
  private VoucherUsageRepository voucherUsageRepository;

  @Autowired
  private VoucherRepository voucherRepository;

  @Autowired
  private FoodOrderDetailRepository foodOrderDetailRepository;

  @Autowired
  private TicketDetailRepository ticketDetailRepository;

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Autowired
  private GenericConverter genericConverter;

  @Override
  public List<VoucherUsageDTO> findAll() {
    List<VoucherUsageDTO> result = new ArrayList<>();
    List<VoucherUsageEntity> entities = voucherUsageRepository.findAll();

    for (VoucherUsageEntity entity : entities) {
      VoucherEntity voucher = voucherRepository.findOneById(entity.getVoucher().getId());
      VoucherUsageDTO voucherUsageDTO = (VoucherUsageDTO) genericConverter.toDTO(entity, VoucherUsageDTO.class);
      voucherUsageDTO.setCode(voucher.getCode());
      result.add(voucherUsageDTO);
    }
    return result;
  }

  @Override
  public List<VoucherUsageDTO> findAllWithStatusIsTrue() {
    List<VoucherUsageDTO> result = new ArrayList<>();
    List<VoucherUsageEntity> entities = voucherUsageRepository.findAllByStatusTrue();

    for (VoucherUsageEntity entity : entities) {
      VoucherEntity voucher = voucherRepository.findOneById(entity.getVoucher().getId());
      VoucherUsageDTO voucherUsageDTO = (VoucherUsageDTO) genericConverter.toDTO(entity, VoucherUsageDTO.class);
      voucherUsageDTO.setCode(voucher.getCode());
      result.add(voucherUsageDTO);
    }
    return result;
  }

  @Override
  public VoucherUsageDTO save(VoucherUsageDTO voucherUsageDTO) {
    VoucherUsageEntity voucherUsageEntity = new VoucherUsageEntity();
    VoucherUsageEntity existVoucherAndCodeInOrder = voucherUsageRepository.findAllByOrderDetailIdAndCode(voucherUsageDTO.getOrderDetailId(), voucherUsageDTO.getCode());
    OrderDetailEntity orderDetail;

    double totalPrice = 0.0;
      if(existVoucherAndCodeInOrder != null){
        throw  new RuntimeException("Code " + voucherUsageDTO.getCode() + " has existed in order detail "+ voucherUsageDTO.getOrderDetailId());
      }

      if (voucherUsageDTO.getId() != null) {
        VoucherUsageEntity oldEntity = voucherUsageRepository.getReferenceById(voucherUsageDTO. getId());
        voucherUsageEntity = (VoucherUsageEntity) genericConverter.updateEntity(voucherUsageDTO, oldEntity);
      } else {
        orderDetail = orderDetailRepository.findOneById(voucherUsageDTO.getOrderDetailId());
        VoucherEntity voucher = voucherRepository.findByCode(voucherUsageDTO.getCode());

        List<OrderDetailEntity> orderDetail1 = orderDetailRepository.findOrderDetailByVoucherIdAndUserId(voucher.getId());

        if(orderDetail1 != null && !orderDetail1.isEmpty()){
          throw  new RuntimeException("CUT VOUCHER NAY M XAI ROI OKK!!");
        }

        if (orderDetail.getTotalPrice() < voucher.getRequirePrice()) {
          throw new RuntimeException("Total price of order is "+ orderDetail.getTotalPrice()+ " less than required price is " + voucher.getRequirePrice());
        }


        voucherUsageDTO.setVoucherId(voucher.getId());
        if(orderDetail != null){
          double existPrice = orderDetail.getTotalPrice();
          totalPrice = existPrice - voucher.getValue();
          if(totalPrice <= 0){
            totalPrice = 0.0;
          }
        }
        orderDetail.setTotalPrice(totalPrice);
        orderDetailRepository.save(orderDetail);
        voucherUsageEntity = (VoucherUsageEntity) genericConverter.toEntity(voucherUsageDTO, VoucherUsageEntity.class);
      }

      voucherUsageRepository.save(voucherUsageEntity);
      VoucherUsageDTO result = (VoucherUsageDTO) genericConverter.toDTO(voucherUsageEntity, VoucherUsageDTO.class);
      return result;
  }

  @Override
  public void changeStatus(Long ids) {
    VoucherUsageEntity voucherUsageEntity = voucherUsageRepository.findOneById(ids);
    if (voucherUsageEntity.getStatus() == null || !voucherUsageEntity.getStatus()) {
      voucherUsageEntity.setStatus(true);
    } else {
      voucherUsageEntity.setStatus(false);
    }
    voucherUsageRepository.save(voucherUsageEntity);
  }

  @Override
  public int totalItem() {
    return (int) voucherUsageRepository.count();
  }

  @Override
  public List<VoucherUsageDTO> findAll(Pageable pageable) {
    // Implement this method based on your requirements
    return null;
  }

  public void deleteById(Long id){
    VoucherUsageEntity voucherUsage = voucherUsageRepository.findOneById(id);
    List<FoodOrderDetailEntity> foodOrderDetails =  foodOrderDetailRepository.findAllByOrderDetailId(voucherUsage.getOrderDetail().getId());
    List<TicketDetailEntity> tickets = ticketDetailRepository.findAllByOrderDetailId(voucherUsage.getOrderDetail().getId());
    OrderDetailEntity orderDetail = orderDetailRepository.findOneById(voucherUsage.getOrderDetail().getId());
    double originPrice = 0.0;
    double totalPrice = 0.0;
    for(FoodOrderDetailEntity foodOrderDetail: foodOrderDetails){
      originPrice += foodOrderDetail.getPrice() * foodOrderDetail.getQuantity();
    }
    for (TicketDetailEntity ticketDetail: tickets){
      originPrice += ticketDetail.getPrice();
    }

    if(voucherUsage.getId() != null){
      if (orderDetail != null ){
        double existPrice = orderDetail.getTotalPrice();
        double changePrice = voucherUsage.getVoucher().getValue();
        totalPrice = existPrice + changePrice;
      }
      if(totalPrice > originPrice){
        totalPrice = originPrice;
      }
      orderDetail.setTotalPrice(totalPrice);
      orderDetailRepository.save(orderDetail);
      voucherUsageRepository.deleteById(id);
    }
  }
}
