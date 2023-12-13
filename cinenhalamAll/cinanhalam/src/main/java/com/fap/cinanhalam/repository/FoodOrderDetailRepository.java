package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.FoodOrderDetailEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FoodOrderDetailRepository extends JpaRepository<FoodOrderDetailEntity, Long> {
      List<FoodOrderDetailEntity> findAllByStatusTrue();

      FoodOrderDetailEntity findOneById(Long id);

      List<FoodOrderDetailEntity> findByOrderDetailId(Long id);

      int countByOrderDetailId(Long id);

      @Query("SELECT COALESCE(SUM(fo.price * fo.quantity), 0.0) FROM FoodOrderDetailEntity fo")
      double getTotalPrice();

      @Query(value = "SELECT SUM(fo.price * fo.quantity) AS total " +
          "FROM food_order_detail fo " +
          "WHERE fo.order_detail_id = :orderDetailId", nativeQuery = true)
      Double getTotalPriceByOrderDetailId(@Param("orderDetailId") Long orderDetailId);


      boolean existsByFoodIdAndOrderDetailId(Long foodId, Long orderDetailId);
      List<FoodOrderDetailEntity> findAllByOrderDetailId(Long orderDetailId);
}
