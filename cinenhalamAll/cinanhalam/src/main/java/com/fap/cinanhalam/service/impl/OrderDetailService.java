package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.OrderDetailDTO;
import com.fap.cinanhalam.entity.OrderDetailEntity;
import com.fap.cinanhalam.repository.OrderDetailRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService implements IGenericService<OrderDetailDTO> {

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Autowired
  private GenericConverter genericConverter;

  @Override
  public List<OrderDetailDTO> findAll() {
    List<OrderDetailDTO> result = new ArrayList<>();
    List<OrderDetailEntity> entities = orderDetailRepository.findAll();

    for (OrderDetailEntity entity : entities) {
      OrderDetailDTO orderDetailDTO = (OrderDetailDTO) genericConverter.toDTO(entity, OrderDetailDTO.class);
      result.add(orderDetailDTO);
    }
    return result;
  }

  @Override
  public List<OrderDetailDTO> findAllWithStatusIsTrue() {
    List<OrderDetailDTO> result = new ArrayList<>();
    List<OrderDetailEntity> entities = orderDetailRepository.findAllByStatusTrue();

    for (OrderDetailEntity entity : entities) {
      OrderDetailDTO orderDetailDTO = (OrderDetailDTO) genericConverter.toDTO(entity, OrderDetailDTO.class);
      result.add(orderDetailDTO);
    }
    return result;
  }

  @Override
  public OrderDetailDTO save(OrderDetailDTO orderDetailDTO) {
    OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
    if (orderDetailDTO.getId() != null) {
      OrderDetailEntity oldEntity = orderDetailRepository.findOneById(orderDetailDTO.getId());
      orderDetailEntity = (OrderDetailEntity) genericConverter.updateEntity(orderDetailDTO, oldEntity);
    } else {
      orderDetailEntity = (OrderDetailEntity) genericConverter.toEntity(orderDetailDTO, OrderDetailEntity.class);
    }
    orderDetailRepository.save(orderDetailEntity);
    return (OrderDetailDTO) genericConverter.toDTO(orderDetailEntity, OrderDetailDTO.class);
  }

  @Override
  public void changeStatus(Long ids) {
    OrderDetailEntity orderDetailEntity = orderDetailRepository.findOneById(ids);
    if (orderDetailEntity.getStatus() == true) {
      orderDetailEntity.setStatus(false);
    } else {
      orderDetailEntity.setStatus(true);
    }
    orderDetailRepository.save(orderDetailEntity);
  }

  @Override
  public int totalItem() {
    return (int) orderDetailRepository.count();
  }


  @Override
  public List<OrderDetailDTO> findAll(Pageable pageable) {
    // Implement logic to fetch data with pagination if needed
    return null;
  }
}
