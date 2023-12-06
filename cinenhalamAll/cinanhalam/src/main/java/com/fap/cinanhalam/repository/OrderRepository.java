package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
