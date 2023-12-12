package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.SeatDTO;
import com.fap.cinanhalam.entity.SeatEntity;
import com.fap.cinanhalam.repository.SeatRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService implements IGenericService<SeatDTO> {

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    GenericConverter genericConverter;

    @Override
    public List<SeatDTO> findAll() {
        List<SeatDTO> result = new ArrayList<>();
        List<SeatEntity> entities = seatRepository.findAll();
        for (SeatEntity entity : entities) {
            SeatDTO seatDTO = (SeatDTO) genericConverter.toDTO(entity, SeatDTO.class);
            result.add(seatDTO);
        }
        return result;
    }

    @Override
    public List<SeatDTO> findAllWithStatusIsTrue() {
        List<SeatDTO> result = new ArrayList<>();
        List<SeatEntity> entities = seatRepository.findAllByStatusTrue();
        for (SeatEntity entity : entities) {
            SeatDTO seatDTO = (SeatDTO) genericConverter.toDTO(entity, SeatDTO.class);
            result.add(seatDTO);
        }
        return result;
    }

    @Override
    public SeatDTO save(SeatDTO seatDTO) {
        SeatEntity seatEntity = new SeatEntity();
        if (seatDTO.getId() != null) {
            SeatEntity oldEntity = seatRepository.findOneById(seatDTO.getId());
            seatEntity = (SeatEntity) genericConverter.updateEntity(seatDTO, oldEntity);
        } else {
            seatEntity = (SeatEntity) genericConverter.toEntity(seatDTO, SeatEntity.class);
        }
        seatRepository.save(seatEntity);
        return (SeatDTO) genericConverter.toDTO(seatEntity, SeatDTO.class);
    }

    @Override
    public void changeStatus(Long ids) {
        SeatEntity seatEntity = seatRepository.findOneById(ids);
        if (seatEntity.getStatus() == true) {
            seatEntity.setStatus(false);
        } else {
            seatEntity.setStatus(true);
        }
        seatRepository.save(seatEntity);
    }

    @Override
    public int totalItem() {
        return (int) seatRepository.count();
    }

    @Override
    public List<SeatDTO> findAll(Pageable pageable) {
        // Implement pagination logic if needed
        return null;
    }
}
