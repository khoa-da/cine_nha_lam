package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.ScheduleDTO;
import com.fap.cinanhalam.entity.FilmEntity;
import com.fap.cinanhalam.entity.ScheduleEntity;
import com.fap.cinanhalam.repository.FilmRepository;
import com.fap.cinanhalam.repository.ScheduleRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ScheduleService implements IGenericService<ScheduleDTO> {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    GenericConverter genericConverter;

    @Override
    public List<ScheduleDTO> findAll() {
        List<ScheduleDTO> result = new ArrayList<>();
        List<ScheduleEntity> entities = scheduleRepository.findAll();
        for (ScheduleEntity entity: entities){
            ScheduleDTO scheduleDTO = (ScheduleDTO) genericConverter.toDTO(entity, ScheduleDTO.class);
            result.add(scheduleDTO);
        }
        return result;
    }

    @Override
    public List<ScheduleDTO> findAllWithStatusIsTrue() {
        List<ScheduleDTO> result = new ArrayList<>();
        List<ScheduleEntity> entities = scheduleRepository.findAllByStatusTrue();
        for(ScheduleEntity entity : entities){
            ScheduleDTO scheduleDTO = (ScheduleDTO) genericConverter.toDTO(entity, ScheduleDTO.class);
            result.add(scheduleDTO);
        }
        return result;
    }

    @Override
    public ScheduleDTO save(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        if (scheduleDTO.getId() != null) {
            ScheduleEntity oldEntity = scheduleRepository.findOneById(scheduleDTO.getId());
            scheduleEntity = (ScheduleEntity) genericConverter.updateEntity(scheduleDTO, oldEntity);
        } else {
            String film = scheduleDTO.getFilmName();
            FilmEntity existingFilm = filmRepository.findOneByNameAndStatusTrue(film);
            if(existingFilm != null) {
                scheduleEntity = (ScheduleEntity) genericConverter.toEntity(scheduleDTO, ScheduleEntity.class);
                scheduleEntity.setFilm(existingFilm);
            }else{
                throw new RuntimeException("Film with name " + film + " not found.");
            }
        }
        scheduleRepository.save(scheduleEntity);
        return (ScheduleDTO) genericConverter.toDTO(scheduleEntity, ScheduleDTO.class);
    }

    @Override
    public void changeStatus(Long ids) {
        ScheduleEntity scheduleEntity = scheduleRepository.findOneById(ids);
        if (scheduleEntity.getStatus() == true) {
            scheduleEntity.setStatus(false);
        } else {
            scheduleEntity.setStatus(true);
        }
        scheduleRepository.save(scheduleEntity);
    }

    @Override
    public int totalItem() {
        return (int)scheduleRepository.count();
    }

    @Override
    public List<ScheduleDTO> findAll(Pageable pageable) {
        return null;
    }
}