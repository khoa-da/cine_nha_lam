package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.FilmCategoryDTO;
import com.fap.cinanhalam.entity.FilmCategoryEntity;
import com.fap.cinanhalam.repository.FilmCategoryRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FilmCategoryService implements IGenericService<FilmCategoryDTO> {

    @Autowired
    private FilmCategoryRepository filmCategoryRepository;

    @Autowired
    GenericConverter genericConverter;

    @Override
    public List<FilmCategoryDTO> findAll() {
        List<FilmCategoryDTO> result = new ArrayList<>();
        List<FilmCategoryEntity> entities = filmCategoryRepository.findAll();
        for (FilmCategoryEntity entity: entities){
            FilmCategoryDTO filmCategoryDTO = (FilmCategoryDTO) genericConverter.toDTO(entity, FilmCategoryDTO.class);
            result.add(filmCategoryDTO);
        }
        return result;
    }

    @Override
    public List<FilmCategoryDTO> findAllWithStatusIsTrue() {
        List<FilmCategoryDTO> result = new ArrayList<>();
        List<FilmCategoryEntity> entities = filmCategoryRepository.findAllByStatusTrue();
        for(FilmCategoryEntity entity : entities){
            FilmCategoryDTO filmCategoryDTO = (FilmCategoryDTO) genericConverter.toDTO(entity, FilmCategoryDTO.class);
            result.add(filmCategoryDTO);
        }
        return result;
    }

    @Override
    public FilmCategoryDTO save(FilmCategoryDTO filmCategoryDTO) {
        FilmCategoryEntity filmCategoryEntity = new FilmCategoryEntity();
        if (filmCategoryDTO.getId() != null) {
            FilmCategoryEntity oldEntity = filmCategoryRepository.findOneById(filmCategoryDTO.getId());
            filmCategoryEntity = (FilmCategoryEntity) genericConverter.updateEntity(filmCategoryDTO, oldEntity);
        } else {
            filmCategoryEntity = (FilmCategoryEntity) genericConverter.toEntity(filmCategoryDTO, FilmCategoryEntity.class);
        }
        filmCategoryRepository.save(filmCategoryEntity);
        return (FilmCategoryDTO) genericConverter.toDTO(filmCategoryEntity, FilmCategoryDTO.class);
    }

    @Override
    public void changeStatus(Long ids) {
        FilmCategoryEntity filmCategoryEntity = filmCategoryRepository.findOneById(ids);
        if (filmCategoryEntity.getStatus() == true) {
            filmCategoryEntity.setStatus(false);
        } else {
            filmCategoryEntity.setStatus(true);
        }
        filmCategoryRepository.save(filmCategoryEntity);
    }

    @Override
    public int totalItem() {
        return (int)filmCategoryRepository.count();
    }

    @Override
    public List<FilmCategoryDTO> findAll(Pageable pageable) {
        List<FilmCategoryDTO> result = new ArrayList<>();
        List<FilmCategoryEntity> entities = filmCategoryRepository.findAll(pageable).getContent();
        for (FilmCategoryEntity entity: entities){
            FilmCategoryDTO filmCategoryDTO = (FilmCategoryDTO) genericConverter.toDTO(entity, FilmCategoryDTO.class);
            result.add(filmCategoryDTO);
        }
        return result;
    }
}