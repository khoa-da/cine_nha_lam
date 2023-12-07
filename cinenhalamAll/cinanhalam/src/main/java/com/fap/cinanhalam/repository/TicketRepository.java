package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.TicketEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
  List<TicketEntity> findAllByStatusTrue();
  TicketEntity findOneById(Long id);
}
