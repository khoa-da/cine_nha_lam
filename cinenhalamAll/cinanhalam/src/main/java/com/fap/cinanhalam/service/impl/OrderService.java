package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.OrderDTO;
import com.fap.cinanhalam.entity.FoodOrderDetailEntity;
import com.fap.cinanhalam.entity.OrderDetailEntity;
import com.fap.cinanhalam.entity.OrderEntity;
import com.fap.cinanhalam.repository.FoodOrderDetailRepository;
import com.fap.cinanhalam.repository.OrderDetailRepository;
import com.fap.cinanhalam.repository.OrderRepository;
import com.fap.cinanhalam.repository.TicketDetailRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IGenericService<OrderDTO> {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Autowired
  private FoodOrderDetailRepository foodOrderDetailRepository;

  @Autowired
  private TicketDetailRepository ticketDetailRepository;

  @Autowired
  private GenericConverter genericConverter;

  @Override
  public List<OrderDTO> findAll() {
    List<OrderDTO> result = new ArrayList<>();
    List<OrderEntity> entities = orderRepository.findAll();

    for (OrderEntity entity : entities) {
      double totalPrice = 0.0;
      List<OrderDetailEntity> orderDetails = orderDetailRepository.findAllByOrderId(entity.getId());
      for(OrderDetailEntity orderDetail: orderDetails){
        totalPrice += orderDetail.getTotalPrice();
      }
      //luu totalPrice vao Order
      OrderEntity order = orderRepository.findOneById(entity.getId());
      order.setTotalPrice(totalPrice);
      orderRepository.save(order);

      OrderDTO orderDTO = (OrderDTO) genericConverter.toDTO(entity, OrderDTO.class);
      result.add(orderDTO);
    }
    return result;
  }

  @Override
  public List<OrderDTO> findAllWithStatusIsTrue() {
    List<OrderDTO> result = new ArrayList<>();
    List<OrderEntity> entities = orderRepository.findAllByStatusTrue();

    for (OrderEntity entity : entities) {
      double totalPrice = 0.0;
      List<OrderDetailEntity> orderDetails = orderDetailRepository.findAllByOrderId(entity.getId());
      for(OrderDetailEntity orderDetail: orderDetails){
        totalPrice += orderDetail.getTotalPrice();
      }
      //lưu totalPrice vào Order
      OrderEntity order = orderRepository.findOneById(entity.getId());
      order.setTotalPrice(totalPrice);
      orderRepository.save(order);

      OrderDTO orderDTO = (OrderDTO) genericConverter.toDTO(entity, OrderDTO.class);
      result.add(orderDTO);
    }
    return result;
  }

  @Override
  public OrderDTO save(OrderDTO orderDTO) {
    OrderEntity orderEntity = new OrderEntity();
    if (orderDTO.getId() != null) {
      OrderEntity oldEntity = orderRepository.findOneById(orderDTO.getId());
      orderEntity = (OrderEntity) genericConverter.updateEntity(orderDTO, oldEntity);
    } else {
      orderEntity = (OrderEntity) genericConverter.toEntity(orderDTO, OrderEntity.class);
    }
    orderRepository.save(orderEntity);
    return (OrderDTO) genericConverter.toDTO(orderEntity, OrderDTO.class);
  }

  @Override
  public void changeStatus(Long ids) {
    OrderEntity orderEntity = orderRepository.findOneById(ids);
    if (orderEntity.getStatus() == null || !orderEntity.getStatus()) {
      orderEntity.setStatus(true);
    } else {
      orderEntity.setStatus(false);
    }
    orderRepository.save(orderEntity);
  }

  @Override
  public int totalItem() {
    return (int) orderRepository.count();
  }

  @Override
  public List<OrderDTO> findAll(Pageable pageable) {
    // Implement this method based on your requirements
    return null;
  }
}
