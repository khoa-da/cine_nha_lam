package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.RoomDTO;
import com.fap.cinanhalam.entity.RoomEntity;
import com.fap.cinanhalam.repository.RoomRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService implements IGenericService<RoomDTO> {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    GenericConverter genericConverter;

    @Override
    public List<RoomDTO> findAll() {
        List<RoomDTO> result = new ArrayList<>();
        List<RoomEntity> entities = roomRepository.findAll();
        for (RoomEntity entity : entities) {
            RoomDTO roomDTO = (RoomDTO) genericConverter.toDTO(entity, RoomDTO.class);
            result.add(roomDTO);
        }
        return result;
    }

    @Override
    public List<RoomDTO> findAllWithStatusIsTrue() {
        List<RoomDTO> result = new ArrayList<>();
        List<RoomEntity> entities = roomRepository.findAllByStatusTrue();
        for (RoomEntity entity : entities) {
            RoomDTO roomDTO = (RoomDTO) genericConverter.toDTO(entity, RoomDTO.class);
            result.add(roomDTO);
        }
        return result;
    }

    @Override
    public RoomDTO save(RoomDTO roomDTO) {
        RoomEntity roomEntity = new RoomEntity();
        if (roomDTO.getId() != null) {
            RoomEntity oldEntity = roomRepository.findOneById(roomDTO.getId());
            roomEntity = (RoomEntity) genericConverter.updateEntity(roomDTO, oldEntity);
        } else {
            roomEntity = (RoomEntity) genericConverter.toEntity(roomDTO, RoomEntity.class);
        }
        roomRepository.save(roomEntity);
        return (RoomDTO) genericConverter.toDTO(roomEntity, RoomDTO.class);
    }

    @Override
    public void changeStatus(Long ids) {
        RoomEntity roomEntity = roomRepository.findOneById(ids);
        if (roomEntity.getStatus() == true) {
            roomEntity.setStatus(false);
        } else {
            roomEntity.setStatus(true);
        }
        roomRepository.save(roomEntity);
    }

    @Override
    public int totalItem() {
        return (int) roomRepository.count();
    }

    @Override
    public List<RoomDTO> findAll(Pageable pageable) {
        // Implement pagination logic if needed
        return null;
    }
}
