package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.VoucherUsageDTO;
import com.fap.cinanhalam.entity.VoucherUsageEntity;
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
  private GenericConverter genericConverter;

  @Override
  public List<VoucherUsageDTO> findAll() {
    List<VoucherUsageDTO> result = new ArrayList<>();
    List<VoucherUsageEntity> entities = voucherUsageRepository.findAll();

    for (VoucherUsageEntity entity : entities) {
      VoucherUsageDTO voucherUsageDTO = (VoucherUsageDTO) genericConverter.toDTO(entity, VoucherUsageDTO.class);
      result.add(voucherUsageDTO);
    }
    return result;
  }

  @Override
  public List<VoucherUsageDTO> findAllWithStatusIsTrue() {
    List<VoucherUsageDTO> results = new ArrayList<>();
    List<VoucherUsageEntity> entities = voucherUsageRepository.findAllByStatusTrue();

    for (VoucherUsageEntity item : entities) {
      VoucherUsageDTO newDTO = (VoucherUsageDTO) genericConverter.toDTO(item, VoucherUsageDTO.class);
      results.add(newDTO);
    }
    return results;
  }

  @Override
  public VoucherUsageDTO save(VoucherUsageDTO voucherUsageDTO) {
    VoucherUsageEntity voucherUsageEntity = new VoucherUsageEntity();
    if (voucherUsageDTO.getId() != null) {
      VoucherUsageEntity oldEntity = voucherUsageRepository.getReferenceById(voucherUsageDTO.getId());
      voucherUsageEntity = (VoucherUsageEntity) genericConverter.updateEntity(voucherUsageDTO, oldEntity);
    } else {
      voucherUsageEntity = (VoucherUsageEntity) genericConverter.toEntity(voucherUsageDTO, VoucherUsageEntity.class);
    }
    voucherUsageRepository.save(voucherUsageEntity);
    return (VoucherUsageDTO) genericConverter.toDTO(voucherUsageEntity, VoucherUsageDTO.class);
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
}
