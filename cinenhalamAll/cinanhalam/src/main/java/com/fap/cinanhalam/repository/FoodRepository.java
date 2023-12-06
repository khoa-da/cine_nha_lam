package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.FoodEntity;
import com.fap.cinanhalam.entity.PostEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
  List<FoodEntity> findAllByStatusTrue();
  FoodEntity findOneById(Long id);
}
