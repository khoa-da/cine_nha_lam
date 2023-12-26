package com.fap.cinanhalam.repository;


import com.fap.cinanhalam.entity.VoucherUsageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherUsageRepository extends JpaRepository<VoucherUsageEntity, Long> {
  List<VoucherUsageEntity> findAllByStatusTrue();

  VoucherUsageEntity findOneById(Long id);

  VoucherUsageEntity findAllByOrderDetailIdAndCode(Long orderId, String code);

List<VoucherUsageEntity> findAllByOrderDetailId(Long orderDetailId);
}
