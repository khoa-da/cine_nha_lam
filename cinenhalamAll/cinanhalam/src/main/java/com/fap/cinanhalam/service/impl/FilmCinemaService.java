package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.FilmCinemaDTO;
import com.fap.cinanhalam.entity.FilmCinemaEntity;
import com.fap.cinanhalam.repository.FilmCinemaRepository;
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
            filmCinemaEntity = (FilmCinemaEntity) genericConverter.toEntity(filmCinemaDTO, FilmCinemaEntity.class);
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
        List<FilmCinemaDTO> result = new ArrayList<>();
        List<FilmCinemaEntity> entities = filmCinemaRepository.findAll(pageable).getContent();
        for (FilmCinemaEntity entity: entities){
            FilmCinemaDTO filmCinemaDTO = (FilmCinemaDTO) genericConverter.toDTO(entity, FilmCinemaDTO.class);
            result.add(filmCinemaDTO);
        }
        return result;
    }
}