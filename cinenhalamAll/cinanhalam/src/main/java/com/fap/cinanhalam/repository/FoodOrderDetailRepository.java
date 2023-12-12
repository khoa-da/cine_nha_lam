package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.FoodOrderDetailEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderDetailRepository extends JpaRepository<FoodOrderDetailEntity, Long> {
      List<FoodOrderDetailEntity> findAllByStatusTrue();

      FoodOrderDetailEntity findOneById(Long id);

      List<FoodOrderDetailEntity> findByOrderDetailId(Long id);

      int countByOrderDetailId(Long id);

      boolean existsByFoodIdAndOrderDetailId(Long foodId, Long orderDetailId);
}
