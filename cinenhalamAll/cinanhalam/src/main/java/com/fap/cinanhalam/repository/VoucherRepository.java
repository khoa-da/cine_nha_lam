package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.VoucherEntity;
import com.fap.cinanhalam.entity.VoucherUsageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<VoucherEntity, Long> {
  List<VoucherEntity> findAllByStatusTrue();

  VoucherEntity findOneById(Long id);

  VoucherEntity findByCode(String code);
}
