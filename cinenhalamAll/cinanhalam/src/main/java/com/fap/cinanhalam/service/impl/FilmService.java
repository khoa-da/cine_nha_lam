package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.FilmDTO;
import com.fap.cinanhalam.entity.FilmEntity;
import com.fap.cinanhalam.repository.FilmRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FilmService implements IGenericService<FilmDTO> {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    GenericConverter genericConverter;

    @Override
    public List<FilmDTO> findAll() {
        List<FilmDTO> result = new ArrayList<>();
        List<FilmEntity> entities = filmRepository.findAll();
        for (FilmEntity entity: entities){
            FilmDTO filmDTO = (FilmDTO) genericConverter.toDTO(entity, FilmDTO.class);
            result.add(filmDTO);
        }
        return result;
    }

    @Override
    public List<FilmDTO> findAllWithStatusIsTrue() {
        List<FilmDTO> result = new ArrayList<>();
        List<FilmEntity> entities = filmRepository.findAllByStatusTrue();
        for(FilmEntity entity : entities){
            FilmDTO filmDTO = (FilmDTO) genericConverter.toDTO(entity, FilmDTO.class);
            result.add(filmDTO);
        }
        return result;
    }

    @Override
    public FilmDTO save(FilmDTO filmDTO) {
        FilmEntity filmEntity = new FilmEntity();
        if (filmDTO.getId() != null) {
            FilmEntity oldEntity = filmRepository.findOneById(filmDTO.getId());
            filmEntity = (FilmEntity) genericConverter.updateEntity(filmDTO, oldEntity);
        } else {
            filmEntity = (FilmEntity) genericConverter.toEntity(filmDTO, FilmEntity.class);
        }
        filmRepository.save(filmEntity);
        return (FilmDTO) genericConverter.toDTO(filmEntity, FilmDTO.class);
    }

    @Override
    public void changeStatus(Long ids) {
        FilmEntity filmEntity = filmRepository.findOneById(ids);
        if (filmEntity.getStatus() == true) {
            filmEntity.setStatus(false);
        } else {
            filmEntity.setStatus(true);
        }
        filmRepository.save(filmEntity);
    }

    @Override
    public int totalItem() {
        return (int)filmRepository.count();
    }

    @Override
    public List<FilmDTO> findAll(Pageable pageable) {
        List<FilmDTO> result = new ArrayList<>();
        List<FilmEntity> entities = filmRepository.findAll(pageable).getContent();
        for (FilmEntity entity: entities){
            FilmDTO filmDTO = (FilmDTO) genericConverter.toDTO(entity, FilmDTO.class);
            result.add(filmDTO);
        }
        return result;
    }
}