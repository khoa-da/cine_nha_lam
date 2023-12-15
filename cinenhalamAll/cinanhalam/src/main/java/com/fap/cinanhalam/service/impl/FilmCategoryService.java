package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.FilmCategoryDTO;
import com.fap.cinanhalam.dto.FilmDTO;
import com.fap.cinanhalam.entity.CategoryEntity;
import com.fap.cinanhalam.entity.FilmCategoryEntity;
import com.fap.cinanhalam.entity.FilmEntity;
import com.fap.cinanhalam.repository.CategoryRepository;
import com.fap.cinanhalam.repository.FilmCategoryRepository;
import com.fap.cinanhalam.repository.FilmRepository;
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
    private CategoryRepository categoryRepository;

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    GenericConverter genericConverter;

    @Override
    public List<FilmCategoryDTO> findAll() {
        List<FilmCategoryDTO> result = new ArrayList<>();
        List<FilmCategoryEntity> entities = filmCategoryRepository.findAll();
        for (FilmCategoryEntity entity : entities) {
            FilmCategoryDTO filmCategoryDTO = (FilmCategoryDTO) genericConverter.toDTO(entity, FilmCategoryDTO.class);
            result.add(filmCategoryDTO);
        }
        return result;
    }

    @Override
    public List<FilmCategoryDTO> findAllWithStatusIsTrue() {
        List<FilmCategoryDTO> result = new ArrayList<>();
        List<FilmCategoryEntity> entities = filmCategoryRepository.findAllByStatusTrue();
        for (FilmCategoryEntity entity : entities) {
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
            Long filmId = filmCategoryDTO.getFilmId();
            Long cateId = filmCategoryDTO.getCategoryId();
            FilmEntity existingFilm = filmRepository.findOneByIdAndStatusTrue(filmId);
            CategoryEntity existingCategory = categoryRepository.findOneByIdAndStatusTrue(cateId);
            if (existingFilm != null && existingCategory != null) {
                filmCategoryEntity = (FilmCategoryEntity) genericConverter.toEntity(filmCategoryDTO, FilmCategoryEntity.class);
                filmCategoryEntity.setFilm(existingFilm);
                filmCategoryEntity.setCategory(existingCategory);
            } else {
                if (existingFilm == null) {
                    throw new RuntimeException("Film with id " + filmId + " not found.");
                } else {
                    throw new RuntimeException("Category with id " + cateId + " not found.");
                }
            }
        }
        filmCategoryRepository.save(filmCategoryEntity);
        return (FilmCategoryDTO) genericConverter.toDTO(filmCategoryEntity, FilmCategoryDTO.class);
    }

    @Override
    public void changeStatus(Long id) {
        FilmCategoryEntity filmCategoryEntity = filmCategoryRepository.findOneById(id);
        if (filmCategoryEntity.getStatus() == true) {
            filmCategoryEntity.setStatus(false);
        } else {
            filmCategoryEntity.setStatus(true);
        }
        filmCategoryRepository.save(filmCategoryEntity);
    }

    @Override
    public int totalItem() {
        return (int) filmCategoryRepository.count();
    }

    @Override
    public List<FilmCategoryDTO> findAll(Pageable pageable) {
        return null;
    }

    public List<String> findAllCategoryNamesByFilmId(Long filmId) {
        return filmCategoryRepository.findCategoryNamesByFilmId(filmId);
    }

    // Tìm phim theo category
    public List<FilmDTO> findAllByCategoryName(String categoryName) {
        List<FilmDTO> result = new ArrayList<>();
        List<FilmEntity> entities = filmCategoryRepository.findAllFilmByCategoryName(categoryName);
        for (FilmEntity filmEntity : entities) {
            FilmEntity foundFilmEntity = filmRepository.findOneByNameAndStatusTrue(filmEntity.getName());
            if (foundFilmEntity != null) {
                FilmDTO filmDTO = (FilmDTO) genericConverter.toDTO(foundFilmEntity, FilmDTO.class);
                result.add(filmDTO);
            }
        }
        return result;
    }

}





