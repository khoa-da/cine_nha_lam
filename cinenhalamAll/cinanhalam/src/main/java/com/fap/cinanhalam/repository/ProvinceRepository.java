package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.PostEntity;
import com.fap.cinanhalam.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<ProvinceEntity, Long>{
    ProvinceEntity findOneById(long id);

    List<ProvinceEntity> findAllByStatusTrue();
}
