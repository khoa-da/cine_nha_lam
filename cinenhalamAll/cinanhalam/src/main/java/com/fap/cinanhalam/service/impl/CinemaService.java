package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.CinemaDTO;
import com.fap.cinanhalam.entity.CinemaEntity;
import com.fap.cinanhalam.entity.ProvinceEntity;
import com.fap.cinanhalam.repository.CinemaRepository;
import com.fap.cinanhalam.repository.ProvinceRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CinemaService implements IGenericService<CinemaDTO> {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

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
            String provinceName = cinemaDTO.getProvinceName();
            ProvinceEntity existingProvince = provinceRepository.findOneByNameAndStatusTrue(provinceName);
            if (existingProvince != null) {
                cinemaEntity = (CinemaEntity) genericConverter.toEntity(cinemaDTO, CinemaEntity.class);
                cinemaEntity.setProvince(existingProvince);
            }else{
                throw new RuntimeException("Province with name " + provinceName + " not found.");
            }
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
        return null;
    }

    public List<CinemaDTO> findAllCinemaByProvinceName(String provinceName) {
        List<CinemaDTO> result = new ArrayList<>();
        List<CinemaEntity> entities = cinemaRepository.findAllByProvinceName(provinceName);
        for(CinemaEntity entity : entities){
            CinemaDTO cinemaDTO = (CinemaDTO) genericConverter.toDTO(entity, CinemaDTO.class);
            result.add(cinemaDTO);
        }
        return result;
    }

    // Tìm ra các rạp theo id film và tên tỉnh
    public List<Object[]> findDistinctCinemaDetailsByFilmIdAndProvinceName(Long filmId, String provinceName, String date) {
        try {
            Date parseDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            return cinemaRepository.findDistinctCinemaDetailsByFilmIdAndProvinceName(filmId, provinceName, parseDate);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}