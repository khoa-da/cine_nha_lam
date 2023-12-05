package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.CinemaDTO;
import com.fap.cinanhalam.entity.CinemaEntity;
import com.fap.cinanhalam.repository.CinemaRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CinemaService implements IGenericService<CinemaDTO> {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    GenericConverter genericConverter;

    @Override
    public List<CinemaDTO> findAll() {
        List<CinemaDTO> result = new ArrayList<>();
        List<CinemaEntity> entities = cinemaRepository.findAll();
        for (CinemaEntity entity: entities){
            CinemaDTO cinemaDTO = (CinemaDTO) genericConverter.toDTO(entity, CinemaDTO.class);
            result.add(cinemaDTO);
        }
        return result;
    }

    @Override
    public List<CinemaDTO> findAllWithStatusIsTrue() {
        List<CinemaDTO> result = new ArrayList<>();
        List<CinemaEntity> entities = cinemaRepository.findAllByStatusTrue();
        for(CinemaEntity entity : entities){
            CinemaDTO cinemaDTO = (CinemaDTO) genericConverter.toDTO(entity, CinemaDTO.class);
            result.add(cinemaDTO);
        }
        return result;
    }

    @Override
    public CinemaDTO save(CinemaDTO cinemaDTO) {
        CinemaEntity cinemaEntity = new CinemaEntity();
        if (cinemaDTO.getId() != null) {
            CinemaEntity oldEntity = cinemaRepository.findOneById(cinemaDTO.getId());
            cinemaEntity = (CinemaEntity) genericConverter.updateEntity(cinemaDTO, oldEntity);
        } else {
            cinemaEntity = (CinemaEntity) genericConverter.toEntity(cinemaDTO, CinemaEntity.class);
        }
        cinemaRepository.save(cinemaEntity);
        return (CinemaDTO) genericConverter.toDTO(cinemaEntity, CinemaDTO.class);
    }

    @Override
    public void changeStatus(Long ids) {
        CinemaEntity cinemaEntity = cinemaRepository.findOneById(ids);
        if (cinemaEntity.getStatus() == true) {
            cinemaEntity.setStatus(false);
        } else {
            cinemaEntity.setStatus(true);
        }
        cinemaRepository.save(cinemaEntity);
    }

    @Override
    public int totalItem() {
        return (int)cinemaRepository.count();
    }

    @Override
    public List<CinemaDTO> findAll(Pageable pageable) {
        List<CinemaDTO> result = new ArrayList<>();
        List<CinemaEntity> entities = cinemaRepository.findAll(pageable).getContent();
        for (CinemaEntity entity: entities){
            CinemaDTO cinemaDTO = (CinemaDTO) genericConverter.toDTO(entity, CinemaDTO.class);
            result.add(cinemaDTO);
        }
        return result;
    }
}