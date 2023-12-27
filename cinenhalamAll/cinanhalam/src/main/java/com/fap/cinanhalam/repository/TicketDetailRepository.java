package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.FoodOrderDetailEntity;
import com.fap.cinanhalam.entity.TicketDetailEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketDetailRepository extends JpaRepository<TicketDetailEntity, Long> {
  List<TicketDetailEntity> findAllByStatusTrue();

  TicketDetailEntity findOneById(Long id);


  List<TicketDetailEntity> findAllByOrderDetailId(Long orderDetailId);


  @Query("SELECT SUM(t.price) FROM TicketDetailEntity t WHERE t.orderDetail.id = :orderDetailId")
  Double getTotalPriceByOrderDetailId(@Param("orderDetailId") Long orderDetailId);
}
