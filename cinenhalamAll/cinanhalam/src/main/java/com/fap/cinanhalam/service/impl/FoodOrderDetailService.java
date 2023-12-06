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
    // Implement this method based on your requirements
    return null;
  }

  @Override
  public FoodOrderDetailDTO save(FoodOrderDetailDTO foodOrderDetailDTO) {
    // Implement this method based on your requirements
    return null;
  }

  @Override
  public void changeStatus(Long ids) {
    // Implement this method based on your requirements
  }

  @Override
  public int totalItem() {
    // Implement this method based on your requirements
    return 0;
  }

  @Override
  public List<FoodOrderDetailDTO> findAll(Pageable pageable) {
    return null;
  }


}
