package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.FoodOrderDetailEntity;
import com.fap.cinanhalam.entity.TicketDetailEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketDetailRepository extends JpaRepository<TicketDetailEntity, Long> {
  List<TicketDetailEntity> findAllByStatusTrue();

  TicketDetailEntity findOneById(Long id);

  List<TicketDetailEntity> findByOrderDetailId(Long id);
}
