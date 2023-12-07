package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.FoodOrderDetailDTO;
import com.fap.cinanhalam.entity.FoodOrderDetailEntity;
import com.fap.cinanhalam.repository.FoodOrderDetailRepository;
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
    FoodOrderDetailEntity foodOrderDetailEntity = new FoodOrderDetailEntity();
    if (foodOrderDetailDTO.getId() != null) {
      FoodOrderDetailEntity oldEntity = foodOrderDetailRepository.getReferenceById(foodOrderDetailDTO.getId());
      foodOrderDetailEntity = (FoodOrderDetailEntity) genericConverter.updateEntity(foodOrderDetailDTO, oldEntity);
    } else {
      foodOrderDetailEntity = (FoodOrderDetailEntity) genericConverter.toEntity(foodOrderDetailDTO, FoodOrderDetailEntity.class);
    }
    foodOrderDetailRepository.save(foodOrderDetailEntity);
    return (FoodOrderDetailDTO) genericConverter.toDTO(foodOrderDetailEntity, FoodOrderDetailDTO.class);
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
