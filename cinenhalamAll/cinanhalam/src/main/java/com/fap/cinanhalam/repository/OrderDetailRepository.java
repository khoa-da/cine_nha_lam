package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.FoodEntity;
import com.fap.cinanhalam.entity.OrderDetailEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
  List<OrderDetailEntity> findAllByStatusTrue();
  OrderDetailEntity findOneById(Long id);
}
