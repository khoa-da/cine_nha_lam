package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.FoodDTO;
import com.fap.cinanhalam.dto.FoodOrderDetailDTO;
import com.fap.cinanhalam.entity.FoodEntity;
import com.fap.cinanhalam.entity.FoodOrderDetailEntity;
import com.fap.cinanhalam.entity.OrderDetailEntity;
import com.fap.cinanhalam.entity.OrderEntity;
import com.fap.cinanhalam.repository.FoodOrderDetailRepository;
import com.fap.cinanhalam.repository.FoodRepository;
import com.fap.cinanhalam.repository.OrderDetailRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodOrderDetailService implements IGenericService<FoodOrderDetailDTO> {

  @Autowired
  private FoodOrderDetailRepository foodOrderDetailRepository;

  @Autowired
  private FoodRepository foodRepository;

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Autowired
  private GenericConverter genericConverter;

  @Override
  public List<FoodOrderDetailDTO> findAll() {
    List<FoodOrderDetailDTO> result = new ArrayList<>();
    List<FoodOrderDetailEntity> entities = foodOrderDetailRepository.findAll();
    for (FoodOrderDetailEntity entity : entities) {
      FoodOrderDetailDTO dto = (FoodOrderDetailDTO) genericConverter.toDTO(entity, FoodOrderDetailDTO.class);
      result.add(dto);
    }
    return result;
  }

  @Override
  public List<FoodOrderDetailDTO> findAllWithStatusIsTrue() {
    List<FoodOrderDetailDTO> result = new ArrayList<>();
    List<FoodOrderDetailEntity> entities = foodOrderDetailRepository.findAllByStatusTrue();

    for (FoodOrderDetailEntity entity : entities) {
      FoodOrderDetailDTO dto = (FoodOrderDetailDTO) genericConverter.toDTO(entity, FoodOrderDetailDTO.class);
      result.add(dto);
    }
    return result;
  }

  @Override
  public FoodOrderDetailDTO save(FoodOrderDetailDTO foodOrderDetailDTO) {
    FoodOrderDetailEntity foodOrderDetailEntity;
    OrderDetailEntity orderDetail;
    FoodEntity food;

    double totalPrice = 0.0;
    double newPrice = 0.0;
//vùng update
    if (foodOrderDetailDTO.getId() != null ) {
      FoodOrderDetailEntity oldEntity = foodOrderDetailRepository.findOneById(foodOrderDetailDTO.getId());
      orderDetail = orderDetailRepository.findOneById(oldEntity.getOrderDetail().getId());
      food = foodRepository.findOneById(oldEntity.getFood().getId());
//khu add sẳn vào DTO để giảm thiểu chuỗi JSON trước khi update
      foodOrderDetailDTO.setPrice(food.getPrice());
      foodOrderDetailDTO.setOrderDetailId(oldEntity.getOrderDetail().getId());
      foodOrderDetailDTO.setFoodId(oldEntity.getFood().getId());
//khu add TotalPrice vào Order Detail
      newPrice = foodOrderDetailDTO.getQuantity() * food.getPrice();
      if(orderDetail != null){
        double existPrice = oldEntity.getPrice() * oldEntity.getQuantity();
        double changePrice = orderDetail.getTotalPrice() - existPrice; //oldTotalPrice - existPrice
        totalPrice += (changePrice + newPrice) ;
      }
      orderDetail.setTotalPrice(totalPrice);
      orderDetailRepository.save(orderDetail);
      
      foodOrderDetailEntity = (FoodOrderDetailEntity) genericConverter.updateEntity(foodOrderDetailDTO, oldEntity);
//vùng add
    } else {
      orderDetail = orderDetailRepository.findOneById(foodOrderDetailDTO.getOrderDetailId());
      food = foodRepository.findOneById(foodOrderDetailDTO.getFoodId());
      foodOrderDetailEntity = (FoodOrderDetailEntity) genericConverter.toEntity(foodOrderDetailDTO, FoodOrderDetailEntity.class);
      foodOrderDetailEntity.setFood(food);
      foodOrderDetailEntity.setPrice(food.getPrice());
//khu add TotalPrice vào Order Detail
      newPrice = foodOrderDetailDTO.getQuantity() * food.getPrice();
      if(orderDetail != null){
        double oldTotalPrice = orderDetail.getTotalPrice();
        totalPrice += oldTotalPrice + newPrice ;
      }else{
        totalPrice += newPrice;
      }
      orderDetail.setTotalPrice(totalPrice);
      orderDetailRepository.save(orderDetail);
    }
    foodOrderDetailRepository.save(foodOrderDetailEntity);
    FoodOrderDetailDTO result = (FoodOrderDetailDTO) genericConverter.toDTO(foodOrderDetailEntity, FoodOrderDetailDTO.class);
    return result;
  }

  @Override
  public void changeStatus(Long ids) {
    FoodOrderDetailEntity foodOrderDetailEntity = foodOrderDetailRepository.findOneById(ids);
    if (foodOrderDetailEntity.getStatus() == true) {
      foodOrderDetailEntity.setStatus(false);
    } else {
      foodOrderDetailEntity.setStatus(true);
    }
    foodOrderDetailRepository.save(foodOrderDetailEntity);
  }

  @Override
  public int totalItem() {
    return (int) foodOrderDetailRepository.count();
  }

  @Override
  public List<FoodOrderDetailDTO> findAll(Pageable pageable) {
    // Implement this method based on your requirements
    return null;
  }

  public FoodOrderDetailDTO findById(Long id) {
    FoodOrderDetailEntity entity = foodOrderDetailRepository.findOneById(id);
    FoodOrderDetailDTO dto = (FoodOrderDetailDTO) genericConverter.toDTO(entity, FoodOrderDetailDTO.class);
    return dto;
  }

  public void deleteById(Long id){
    FoodOrderDetailEntity foodOrderDetail = foodOrderDetailRepository.findOneById(id);
    OrderDetailEntity orderDetail = orderDetailRepository.findOneById(foodOrderDetail.getOrderDetail().getId());
    double totalPrice = 0.0;
    if(foodOrderDetail.getId() != null){

      //update lại price bên order detail
      if(orderDetail != null){
        double existPrice = foodOrderDetail.getPrice() * foodOrderDetail.getQuantity();
        double changePrice = orderDetail.getTotalPrice() - existPrice; //oldTotalPrice - existPrice
        totalPrice += changePrice;
      }
      orderDetail.setTotalPrice(totalPrice);
      orderDetailRepository.save(orderDetail);

      foodOrderDetailRepository.deleteById(id);
    }
  }
}
