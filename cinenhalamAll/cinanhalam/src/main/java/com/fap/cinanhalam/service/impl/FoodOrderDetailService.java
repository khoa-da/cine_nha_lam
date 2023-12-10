package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.FoodDTO;
import com.fap.cinanhalam.dto.FoodOrderDetailDTO;
import com.fap.cinanhalam.entity.FoodEntity;
import com.fap.cinanhalam.entity.FoodOrderDetailEntity;
import com.fap.cinanhalam.repository.FoodOrderDetailRepository;
import com.fap.cinanhalam.repository.FoodRepository;
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
  private GenericConverter genericConverter;

  @Override
  public List<FoodOrderDetailDTO> findAll() {
    List<FoodOrderDetailDTO> result = new ArrayList<>();
    List<FoodOrderDetailEntity> entities = foodOrderDetailRepository.findAll();
    for (FoodOrderDetailEntity entity : entities) {
      FoodEntity existFood = foodRepository.findOneById(entity.getFood().getId());
      FoodDTO foodDTO = (FoodDTO) genericConverter.toDTO(existFood, FoodDTO.class);
      FoodOrderDetailDTO dto = (FoodOrderDetailDTO) genericConverter.toDTO(entity, FoodOrderDetailDTO.class);
      dto.setFoodDTO(foodDTO);
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
      dto.setFoodDTO(foodDTO);
      result.add(dto);
    }
    return result;
  }

  @Override
  public FoodOrderDetailDTO save(FoodOrderDetailDTO foodOrderDetailDTO) {
    FoodOrderDetailEntity foodOrderDetailEntity;
    FoodEntity existFood = foodRepository.findOneById(foodOrderDetailDTO.getFoodId());
    FoodDTO foodDTO = (FoodDTO) genericConverter.toDTO(existFood, FoodDTO.class);
    if (foodOrderDetailDTO.getId() != null) {
      FoodOrderDetailEntity oldEntity = foodOrderDetailRepository.getReferenceById(foodOrderDetailDTO.getId());
      foodOrderDetailEntity = (FoodOrderDetailEntity) genericConverter.updateEntity(foodOrderDetailDTO, oldEntity);
    } else {
      foodOrderDetailEntity = (FoodOrderDetailEntity) genericConverter.toEntity(foodOrderDetailDTO, FoodOrderDetailEntity.class);
      foodOrderDetailEntity.setFood(existFood);
    }
    foodOrderDetailRepository.save(foodOrderDetailEntity);
    FoodOrderDetailDTO result = (FoodOrderDetailDTO) genericConverter.toDTO(foodOrderDetailEntity, FoodOrderDetailDTO.class);
    result.setFoodDTO(foodDTO);
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
