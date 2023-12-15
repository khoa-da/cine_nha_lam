package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.TicketDetailDTO;
import com.fap.cinanhalam.entity.FoodOrderDetailEntity;
import com.fap.cinanhalam.entity.OrderDetailEntity;
import com.fap.cinanhalam.entity.TicketDetailEntity;
import com.fap.cinanhalam.entity.TicketEntity;
import com.fap.cinanhalam.repository.OrderDetailRepository;
import com.fap.cinanhalam.repository.TicketDetailRepository;
import com.fap.cinanhalam.repository.TicketRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketDetailService implements IGenericService<TicketDetailDTO> {

  @Autowired
  private TicketDetailRepository ticketDetailRepository;

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Autowired
  private GenericConverter genericConverter;

  @Override
  public List<TicketDetailDTO> findAll() {
    List<TicketDetailDTO> result = new ArrayList<>();
    List<TicketDetailEntity> entities = ticketDetailRepository.findAll();

    for (TicketDetailEntity entity : entities) {
      TicketDetailDTO ticketDetailDTO = (TicketDetailDTO) genericConverter.toDTO(entity, TicketDetailDTO.class);
      result.add(ticketDetailDTO);
    }
    return result;
  }

  @Override
  public List<TicketDetailDTO> findAllWithStatusIsTrue() {
    List<TicketDetailDTO> result = new ArrayList<>();
    List<TicketDetailEntity> entities = ticketDetailRepository.findAllByStatusTrue();

    for (TicketDetailEntity entity : entities) {
      TicketDetailDTO ticketDetailDTO = (TicketDetailDTO) genericConverter.toDTO(entity, TicketDetailDTO.class);
      result.add(ticketDetailDTO);
    }
    return result;
  }

  @Override
  public TicketDetailDTO save(TicketDetailDTO ticketDetailDTO) {
    TicketDetailEntity ticketDetailEntity = new TicketDetailEntity();
    TicketEntity findTicket;
    OrderDetailEntity orderDetail;

    double totalPrice = 0.0;
    double newPrice = 0.0;

    if (ticketDetailDTO.getId() != null) {
      TicketDetailEntity oldEntity = ticketDetailRepository.findOneById(ticketDetailDTO.getId());
      orderDetail = orderDetailRepository.findOneById(oldEntity.getOrderDetail().getId());
      findTicket = ticketRepository.findOneById(oldEntity.getTicket().getId());
      if(orderDetail != null){
        double beforePrice = oldEntity.getPrice();
        double changePrice = orderDetail.getTotalPrice() - beforePrice; //gia cũ - gia đã tồn tại
        totalPrice += (changePrice + newPrice) ;
      }
      orderDetail.setTotalPrice(totalPrice);
      orderDetailRepository.save(orderDetail);

      ticketDetailDTO.setPrice(findTicket.getPrice());
      ticketDetailEntity = (TicketDetailEntity) genericConverter.updateEntity(ticketDetailDTO, oldEntity);

    } else {
      findTicket = ticketRepository.findOneById(ticketDetailDTO.getTicketId());
      orderDetail = orderDetailRepository.findOneById(ticketDetailDTO.getOrderDetailId());

      newPrice = findTicket.getPrice();
      ticketDetailEntity = (TicketDetailEntity) genericConverter.toEntity(ticketDetailDTO, TicketDetailEntity.class);
      ticketDetailEntity.setPrice(findTicket.getPrice());

      if(orderDetail != null){
        double oldPrice = orderDetail.getTotalPrice();
        totalPrice += oldPrice + newPrice ;
      }else{
        totalPrice += newPrice;
      }
      orderDetail.setTotalPrice(totalPrice);
      orderDetailRepository.save(orderDetail);

    }
    ticketDetailRepository.save(ticketDetailEntity);
    return (TicketDetailDTO) genericConverter.toDTO(ticketDetailEntity, TicketDetailDTO.class);
  }

  @Override
  public void changeStatus(Long ids) {
    TicketDetailEntity ticketDetailEntity = ticketDetailRepository.findOneById(ids);
    if (ticketDetailEntity.getStatus() == true) {
      ticketDetailEntity.setStatus(false);
    } else {
      ticketDetailEntity.setStatus(true);
    }
    ticketDetailRepository.save(ticketDetailEntity);
  }


  @Override
  public int totalItem() {
    return (int) ticketDetailRepository.count();
  }

  @Override
  public List<TicketDetailDTO> findAll(Pageable pageable) {
    // Implement this method based on your requirements
    return null;
  }
}
