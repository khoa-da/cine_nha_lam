package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.VoucherDTO;
import com.fap.cinanhalam.entity.VoucherEntity;
import com.fap.cinanhalam.repository.VoucherRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoucherService implements IGenericService<VoucherDTO> {

  @Autowired
  private VoucherRepository voucherRepository;

  @Autowired
  private GenericConverter genericConverter;

  @Override
  public List<VoucherDTO> findAll() {
    List<VoucherDTO> result = new ArrayList<>();
    List<VoucherEntity> entities = voucherRepository.findAll();

    for (VoucherEntity entity : entities) {
      VoucherDTO voucherDTO = (VoucherDTO) genericConverter.toDTO(entity, VoucherDTO.class);
      result.add(voucherDTO);
    }
    return result;
  }

  @Override
  public List<VoucherDTO> findAllWithStatusIsTrue() {
    List<VoucherDTO> results = new ArrayList<>();
    List<VoucherEntity> entities = voucherRepository.findAllByStatusTrue();

    for (VoucherEntity item : entities) {
      VoucherDTO newDTO = (VoucherDTO) genericConverter.toDTO(item, VoucherDTO.class);
      results.add(newDTO);
    }
    return results;
  }

  @Override
  public VoucherDTO save(VoucherDTO voucherDTO) {
    VoucherEntity voucherEntity = new VoucherEntity();
    if (voucherDTO.getId() != null) {
      VoucherEntity oldEntity = voucherRepository.getReferenceById(voucherDTO.getId());
      voucherEntity = (VoucherEntity) genericConverter.updateEntity(voucherDTO, oldEntity);
    } else {
      voucherEntity = (VoucherEntity) genericConverter.toEntity(voucherDTO, VoucherEntity.class);
    }
    voucherRepository.save(voucherEntity);
    return (VoucherDTO) genericConverter.toDTO(voucherEntity, VoucherDTO.class);
  }

  @Override
  public void changeStatus(Long ids) {
    VoucherEntity voucherEntity = voucherRepository.findOneById(ids);
    if (voucherEntity.getStatus() == null || !voucherEntity.getStatus()) {
      voucherEntity.setStatus(true);
    } else {
      voucherEntity.setStatus(false);
    }
    voucherRepository.save(voucherEntity);
  }

  @Override
  public int totalItem() {
    return (int) voucherRepository.count();
  }

  @Override
  public List<VoucherDTO> findAll(Pageable pageable) {
    // Implement this method based on your requirements
    return null;
  }
}
