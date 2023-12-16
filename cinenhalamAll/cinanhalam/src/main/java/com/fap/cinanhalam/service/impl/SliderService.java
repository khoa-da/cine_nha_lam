package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.SliderDTO;
import com.fap.cinanhalam.entity.SliderEntity;
import com.fap.cinanhalam.repository.SliderRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SliderService implements IGenericService<SliderDTO> {

    @Autowired
    SliderRepository sliderRepository;

    @Autowired
    GenericConverter genericConverter;

    @Override
    public List<SliderDTO> findAll() {
        List<SliderDTO> result = new ArrayList<>();
        List<SliderEntity> entities = sliderRepository.findAll();
        for (SliderEntity entity : entities) {
            SliderDTO sliderDTO = (SliderDTO) genericConverter.toDTO(entity, SliderDTO.class);
            result.add(sliderDTO);
        }
        return result;
    }

    @Override
    public List<SliderDTO> findAllWithStatusIsTrue() {
        List<SliderDTO> result = new ArrayList<>();
        List<SliderEntity> entities = sliderRepository.findAllByStatusTrue();
        for (SliderEntity entity : entities) {
            SliderDTO sliderDTO = (SliderDTO) genericConverter.toDTO(entity, SliderDTO.class);
            result.add(sliderDTO);
        }
        return result;
    }

    @Override
    public SliderDTO save(SliderDTO sliderDTO) {
        SliderEntity sliderEntity = new SliderEntity();
        if (sliderDTO.getId() != null) {
            SliderEntity oldEntity = sliderRepository.findOneById(sliderDTO.getId());
            sliderEntity = (SliderEntity) genericConverter.updateEntity(sliderDTO, oldEntity);
        } else {
            sliderEntity = (SliderEntity) genericConverter.toEntity(sliderDTO, SliderEntity.class);
        }
        sliderRepository.save(sliderEntity);
        return (SliderDTO) genericConverter.toDTO(sliderEntity, SliderDTO.class);
    }

    @Override
    public void changeStatus(Long ids) {
        SliderEntity sliderEntity = sliderRepository.findOneById(ids);
        if (sliderEntity.getStatus() == true) {
            sliderEntity.setStatus(false);
        } else {
            sliderEntity.setStatus(true);
        }
        sliderRepository.save(sliderEntity);
    }

    @Override
    public int totalItem() {
        return (int) sliderRepository.count();
    }

    @Override
    public List<SliderDTO> findAll(Pageable pageable) {
        // Implement pagination logic if needed
        return null;
    }
}
