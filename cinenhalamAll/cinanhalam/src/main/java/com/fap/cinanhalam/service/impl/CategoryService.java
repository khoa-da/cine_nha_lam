package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.CategoryDTO;
import com.fap.cinanhalam.dto.PostDTO;
import com.fap.cinanhalam.entity.CategoryEntity;
import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.repository.CategoryRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@Service
public class CategoryService implements IGenericService<CategoryDTO> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    GenericConverter genericConverter;

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> result = new ArrayList<>();
        List<CategoryEntity> entities = categoryRepository.findAll();
        for (CategoryEntity entity: entities){
            CategoryDTO categoryDTO = (CategoryDTO) genericConverter.toDTO(entity, CategoryDTO.class);
            result.add(categoryDTO);
        }
        return result;
    }

    @Override
    public List<CategoryDTO> findAllWithStatusIsTrue() {
        List<CategoryDTO> result = new ArrayList<>();
        List<CategoryEntity> entities = categoryRepository.findAllByStatusTrue();
        for(CategoryEntity entity : entities){
            CategoryDTO categoryDTO = (CategoryDTO) genericConverter.toDTO(entity, CategoryDTO.class);
            result.add(categoryDTO);
        }
        return result;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        if (categoryDTO.getId() != null) {
            CategoryEntity oldEntity = categoryRepository.findOneById(categoryDTO.getId());
            categoryEntity = (CategoryEntity) genericConverter.updateEntity(categoryDTO, oldEntity);
        } else {
            categoryEntity = (CategoryEntity) genericConverter.toEntity(categoryDTO, CategoryEntity.class);
        }
        categoryRepository.save(categoryEntity);
        return (CategoryDTO) genericConverter.toDTO(categoryEntity, CategoryDTO.class);
    }

    @Override
    public void changeStatus(Long ids) {
        CategoryEntity categoryEntity = categoryRepository.findOneById(ids);
        if (categoryEntity.getStatus() == true) {
            categoryEntity.setStatus(false);
        } else {
            categoryEntity.setStatus(true);
        }
        categoryRepository.save(categoryEntity);
    }

    @Override
    public int totalItem() {
        return (int)categoryRepository.count();
    }

    @Override
    public List<CategoryDTO> findAll(Pageable pageable) {
        List<CategoryDTO> result = new ArrayList<>();
        List<CategoryEntity> entities = categoryRepository.findAll(pageable).getContent();
        for (CategoryEntity entity: entities){
            CategoryDTO categoryDTO = (CategoryDTO) genericConverter.toDTO(entity, CategoryDTO.class);
            result.add(categoryDTO);
        }
        return result;
    }
}
