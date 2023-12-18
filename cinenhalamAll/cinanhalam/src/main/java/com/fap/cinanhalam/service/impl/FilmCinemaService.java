package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.FilmCinemaDTO;
import com.fap.cinanhalam.entity.*;
import com.fap.cinanhalam.repository.CinemaRepository;
import com.fap.cinanhalam.repository.FilmCinemaRepository;
import com.fap.cinanhalam.repository.FilmRepository;
import com.fap.cinanhalam.repository.ScheduleRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FilmCinemaService implements IGenericService<FilmCinemaDTO> {

    @Autowired
    private FilmCinemaRepository filmCinemaRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    GenericConverter genericConverter;

    @Override
    public List<FilmCinemaDTO> findAll() {
        List<FilmCinemaDTO> result = new ArrayList<>();
        List<FilmCinemaEntity> entities = filmCinemaRepository.findAll();
        for (FilmCinemaEntity entity: entities){
            FilmCinemaDTO filmCinemaDTO = (FilmCinemaDTO) genericConverter.toDTO(entity, FilmCinemaDTO.class);
            result.add(filmCinemaDTO);
        }
        return result;
    }

    @Override
    public List<FilmCinemaDTO> findAllWithStatusIsTrue() {
        List<FilmCinemaDTO> result = new ArrayList<>();
        List<FilmCinemaEntity> entities = filmCinemaRepository.findAllByStatusTrue();
        for(FilmCinemaEntity entity : entities){
            FilmCinemaDTO filmCinemaDTO = (FilmCinemaDTO) genericConverter.toDTO(entity, FilmCinemaDTO.class);
            result.add(filmCinemaDTO);
        }
        return result;
    }

    @Override
    public FilmCinemaDTO save(FilmCinemaDTO filmCinemaDTO) {
        FilmCinemaEntity filmCinemaEntity = new FilmCinemaEntity();
        if (filmCinemaDTO.getId() != null) {
            FilmCinemaEntity oldEntity = filmCinemaRepository.findOneById(filmCinemaDTO.getId());
            filmCinemaEntity = (FilmCinemaEntity) genericConverter.updateEntity(filmCinemaDTO, oldEntity);
        } else {
            Long scheduleId = filmCinemaDTO.getScheduleId();
            Long cinemaId = filmCinemaDTO.getCinemaId();
            ScheduleEntity existingSchedule = scheduleRepository.findOneByIdAndStatusTrue(scheduleId);
            CinemaEntity existingCinema = cinemaRepository.findOneByIdAndStatusTrue(cinemaId);
            if(existingSchedule != null && existingCinema != null) {
                filmCinemaEntity = (FilmCinemaEntity) genericConverter.toEntity(filmCinemaDTO, FilmCinemaEntity.class);
                filmCinemaEntity.setSchedule(existingSchedule);
                filmCinemaEntity.setCinema(existingCinema);
            }else{
                if(existingSchedule == null) {
                    throw new RuntimeException("Schedule with id " + scheduleId + " not found.");
                }else{
                    throw new RuntimeException("Cinema with id " + cinemaId + " not found.");
                }
            }
        }
        filmCinemaRepository.save(filmCinemaEntity);
        return (FilmCinemaDTO) genericConverter.toDTO(filmCinemaEntity, FilmCinemaDTO.class);
    }

    @Override
    public void changeStatus(Long ids) {
        FilmCinemaEntity filmCinemaEntity = filmCinemaRepository.findOneById(ids);
        if (filmCinemaEntity.getStatus() == true) {
            filmCinemaEntity.setStatus(false);
        } else {
            filmCinemaEntity.setStatus(true);
        }
        filmCinemaRepository.save(filmCinemaEntity);
    }

    @Override
    public int totalItem() {
        return (int)filmCinemaRepository.count();
    }

    @Override
    public List<FilmCinemaDTO> findAll(Pageable pageable) {
        return null;
    }

    // Tìm rạp phim theo tỉnh
    public List<FilmCinemaDTO> findAllCinemaByProvinceName(String name) {
        List<FilmCinemaDTO> result = new ArrayList<>();
        List<FilmCinemaEntity> entities = filmCinemaRepository.findAll();
        for(FilmCinemaEntity entity : entities){
            FilmCinemaDTO filmCinemaDTO = (FilmCinemaDTO) genericConverter.toDTO(entity, FilmCinemaDTO.class);
            result.add(filmCinemaDTO);
        }
        return result;
    }

}