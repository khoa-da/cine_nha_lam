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
      FoodEntity existFood = foodRepository.findOneById(entity.getFood().getId());
      FoodDTO foodDTO = (FoodDTO) genericConverter.toDTO(existFood, FoodDTO.class);
      FoodOrderDetailDTO dto = (FoodOrderDetailDTO) genericConverter.toDTO(entity, FoodOrderDetailDTO.class);
//      dto.setFoodList(foodDTO);
      result.add(dto);
    }
    return result;
  }

  @Override
  public List<FoodOrderDetailDTO> findAllWithStatusIsTrue() {
    List<FoodOrderDetailDTO> result = new ArrayList<>();
    List<FoodOrderDetailEntity> entities = foodOrderDetailRepository.findAllByStatusTrue();

    for (FoodOrderDetailEntity entity : entities) {
      FoodEntity existFood = foodRepository.findOneById(entity.getFood().getId());
      FoodDTO foodDTO = (FoodDTO) genericConverter.toDTO(existFood, FoodDTO.class);
      FoodOrderDetailDTO dto = (FoodOrderDetailDTO) genericConverter.toDTO(entity, FoodOrderDetailDTO.class);
//      dto.setFoodList(foodDTO);
      result.add(dto);
    }
    return result;
  }

  @Override
  public FoodOrderDetailDTO save(FoodOrderDetailDTO foodOrderDetailDTO) {
    FoodOrderDetailEntity foodOrderDetailEntity;
    System.out.println(foodOrderDetailDTO.getFoodId());
    FoodEntity findFood = foodRepository.findOneById(foodOrderDetailDTO.getFoodId());
    OrderDetailEntity orderDetail = orderDetailRepository.findOneById(foodOrderDetailDTO.getOrderDetailId());

    List<FoodOrderDetailEntity> foodOrderDetails = foodOrderDetailRepository.findAllByOrderDetailId(foodOrderDetailDTO.getOrderDetailId());

    double totalPrice = 0.0;
    double newPrice = foodOrderDetailDTO.getQuantity() * findFood.getPrice();


    if (foodOrderDetailDTO.getId() != null ) {
      FoodOrderDetailEntity oldEntity = foodOrderDetailRepository.findOneById(foodOrderDetailDTO.getId());
      oldEntity.setPrice(oldEntity.getPrice());
      foodOrderDetailEntity = (FoodOrderDetailEntity) genericConverter.updateEntity(foodOrderDetailDTO, oldEntity);

    } else {
      foodOrderDetailEntity = (FoodOrderDetailEntity) genericConverter.toEntity(foodOrderDetailDTO, FoodOrderDetailEntity.class);
      foodOrderDetailEntity.setFood(findFood);
      foodOrderDetailEntity.setPrice(findFood.getPrice());

      if(foodOrderDetails != null && !foodOrderDetails.isEmpty()){
        double oldPrice = foodOrderDetailRepository.getTotalPriceByOrderDetailId(foodOrderDetailDTO.getOrderDetailId());
        totalPrice += oldPrice + newPrice ;
      }else{
        totalPrice += newPrice;
      }
      orderDetail.setPrice(totalPrice);
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
}
