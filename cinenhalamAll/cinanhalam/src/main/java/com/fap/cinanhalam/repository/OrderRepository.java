package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
  List<OrderEntity> findAllByStatusTrue();

  OrderEntity findOneById(Long id);
}
