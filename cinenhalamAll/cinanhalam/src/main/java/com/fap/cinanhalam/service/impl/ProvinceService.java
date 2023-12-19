package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.ProvinceDTO;
import com.fap.cinanhalam.entity.ProvinceEntity;
import com.fap.cinanhalam.repository.ProvinceRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ProvinceService implements IGenericService<ProvinceDTO> {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    GenericConverter genericConverter;

    @Override
    public List<ProvinceDTO> findAll() {
        List<ProvinceDTO> result = new ArrayList<>();
        List<ProvinceEntity> entities = provinceRepository.findAll();
        for (ProvinceEntity entity: entities){
            ProvinceDTO provinceDTO = (ProvinceDTO) genericConverter.toDTO(entity, ProvinceDTO.class);
            result.add(provinceDTO);
        }
        return result;
    }

    @Override
    public List<ProvinceDTO> findAllWithStatusIsTrue() {
        List<ProvinceDTO> result = new ArrayList<>();
        List<ProvinceEntity> entities = provinceRepository.findAllByStatusTrue();
        for(ProvinceEntity entity : entities){
            ProvinceDTO provinceDTO = (ProvinceDTO) genericConverter.toDTO(entity, ProvinceDTO.class);
            result.add(provinceDTO);
        }
        return result;
    }

    @Override
    public ProvinceDTO save(ProvinceDTO provinceDTO) {
        ProvinceEntity provinceEntity = new ProvinceEntity();
        if (provinceDTO.getId() != null) {
            ProvinceEntity oldEntity = provinceRepository.findOneById(provinceDTO.getId());
            provinceEntity = (ProvinceEntity) genericConverter.updateEntity(provinceDTO, oldEntity);
        } else {
            provinceEntity = (ProvinceEntity) genericConverter.toEntity(provinceDTO, ProvinceEntity.class);
        }
        provinceRepository.save(provinceEntity);
        return (ProvinceDTO) genericConverter.toDTO(provinceEntity, ProvinceDTO.class);
    }

    @Override
    public void changeStatus(Long ids) {
        ProvinceEntity provinceEntity = provinceRepository.findOneById(ids);
        if (provinceEntity.getStatus() == true) {
            provinceEntity.setStatus(false);
        } else {
            provinceEntity.setStatus(true);
        }
        provinceRepository.save(provinceEntity);
    }

    @Override
    public int totalItem() {
        return (int)provinceRepository.count();
    }

    @Override
    public List<ProvinceDTO> findAll(Pageable pageable) {
        return null;
    }

    public List<String> findDistinctProvinceNamesByFilmIdAndScreeningDate(Long filmId, String screeningDate) {
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(screeningDate);
                return provinceRepository.findDistinctProvinceNamesByFilmIdAndScreeningDate(filmId, date);
            }catch(Exception e){
                e.printStackTrace();
            }
        return null;
    }
}