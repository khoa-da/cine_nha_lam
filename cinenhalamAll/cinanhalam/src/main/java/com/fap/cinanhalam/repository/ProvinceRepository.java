package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.PostEntity;
import com.fap.cinanhalam.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<ProvinceEntity, Long>{
    ProvinceEntity findOneById(long id);

    List<ProvinceEntity> findAllByStatusTrue();

    @Query("SELECT p FROM ProvinceEntity p WHERE p.name = :name AND p.status = true")
    ProvinceEntity findOneByNameAndStatusTrue(@Param("name") String name);
}
