package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.FoodEntity;
import com.fap.cinanhalam.entity.OrderDetailEntity;
import com.fap.cinanhalam.entity.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
  List<OrderDetailEntity> findAllByStatusTrue();
  OrderDetailEntity findOneById(Long id);
  List<OrderDetailEntity> findAllByOrderId(Long orderId);

  @Query("SELECT od FROM OrderDetailEntity od "
      + "JOIN od.order o "
      + "JOIN od.voucherUsages vu "
      + "WHERE vu.voucher.id = :voucherId AND o.user.id IS NULL")
  List<OrderDetailEntity> findOrderDetailByVoucherIdAndUserId(@Param("voucherId") Long voucherId);
}
