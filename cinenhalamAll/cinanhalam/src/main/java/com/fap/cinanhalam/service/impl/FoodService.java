package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.FoodDTO;
import com.fap.cinanhalam.dto.PostDTO;
import com.fap.cinanhalam.entity.FoodEntity;
import com.fap.cinanhalam.entity.PostEntity;
import com.fap.cinanhalam.repository.FoodRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService implements IGenericService<FoodDTO> {

  @Autowired
  private FoodRepository foodRepository;

  @Autowired
  GenericConverter genericConverter;

  @Override
  public List<FoodDTO> findAll() {
    List<FoodDTO> result = new ArrayList<>();
    List<FoodEntity> entities = foodRepository.findAll();

    for (FoodEntity entity : entities) {
      FoodDTO foodDTO = (FoodDTO) genericConverter.toDTO(entity, FoodDTO.class);
      result.add(foodDTO);
    }
    return result;
  }

  @Override
  public List<FoodDTO> findAllWithStatusIsTrue() {
    List<FoodDTO> results = new ArrayList();
    List<FoodEntity> entities = foodRepository.findAllByStatusTrue();

    for(FoodEntity item : entities) {
      FoodDTO newDTO = (FoodDTO) genericConverter.toDTO(item,FoodDTO.class);
      results.add(newDTO);
    }
    return results;
  }

  @Override
  public FoodDTO save(FoodDTO foodDTO) {
    FoodEntity foodEntity = new FoodEntity();
    if (foodDTO.getId() != null) {
      FoodEntity oldEntity = foodRepository.findOneById(foodDTO.getId());
      foodEntity = (FoodEntity) genericConverter.updateEntity(foodDTO, oldEntity);
    } else {
      foodEntity = (FoodEntity) genericConverter.toEntity(foodDTO, FoodEntity.class);
    }
    foodRepository.save(foodEntity);
    return (FoodDTO) genericConverter.toDTO(foodEntity, FoodDTO.class);
  }

  @Override
  public void changeStatus(Long ids) {
    FoodEntity foodEntity = foodRepository.findOneById(ids);
    if (foodEntity.getStatus() == true) {
      foodEntity.setStatus(false);
    } else {
      foodEntity.setStatus(true);
    }
    foodRepository.save(foodEntity);
  }

  @Override
  public int totalItem() {
    return (int) foodRepository.count();
  }

  @Override
  public List<FoodDTO> findAll(Pageable pageable) {
    // Implement this method based on your requirements
    return null;
  }
}
