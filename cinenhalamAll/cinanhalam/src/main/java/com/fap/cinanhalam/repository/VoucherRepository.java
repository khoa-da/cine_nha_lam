package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.VoucherEntity;
import com.nimbusds.openid.connect.sdk.assurance.evidences.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<VoucherEntity, Long> {

}
