package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.TicketDetailDTO;
import com.fap.cinanhalam.entity.TicketDetailEntity;
import com.fap.cinanhalam.entity.TicketEntity;
import com.fap.cinanhalam.repository.TicketDetailRepository;
import com.fap.cinanhalam.repository.TicketRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketDetailService implements IGenericService<TicketDetailDTO> {

  @Autowired
  private TicketDetailRepository ticketDetailRepository;

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private GenericConverter genericConverter;

  @Override
  public List<TicketDetailDTO> findAll() {
    List<TicketDetailDTO> result = new ArrayList<>();
    List<TicketDetailEntity> entities = ticketDetailRepository.findAll();

    for (TicketDetailEntity entity : entities) {
      TicketDetailDTO ticketDetailDTO = (TicketDetailDTO) genericConverter.toDTO(entity, TicketDetailDTO.class);
      result.add(ticketDetailDTO);
    }
    return result;
  }

  @Override
  public List<TicketDetailDTO> findAllWithStatusIsTrue() {
    List<TicketDetailDTO> result = new ArrayList<>();
    List<TicketDetailEntity> entities = ticketDetailRepository.findAllByStatusTrue();

    for (TicketDetailEntity entity : entities) {
      TicketDetailDTO ticketDetailDTO = (TicketDetailDTO) genericConverter.toDTO(entity, TicketDetailDTO.class);
      result.add(ticketDetailDTO);
    }
    return result;
  }

  @Override
  public TicketDetailDTO save(TicketDetailDTO ticketDetailDTO) {
    TicketDetailEntity ticketDetailEntity = new TicketDetailEntity();
    TicketEntity findTicket = ticketRepository.findOneById(ticketDetailDTO.getTicketId());

    if (ticketDetailDTO.getId() != null) {
      TicketDetailEntity oldEntity = ticketDetailRepository.findOneById(ticketDetailDTO.getId());
      ticketDetailEntity = (TicketDetailEntity) genericConverter.updateEntity(ticketDetailDTO, oldEntity);
      ticketDetailEntity.setPrice(findTicket.getPrice());
    } else {
      ticketDetailEntity = (TicketDetailEntity) genericConverter.toEntity(ticketDetailDTO, TicketDetailEntity.class);
      ticketDetailEntity.setPrice(findTicket.getPrice());
    }
    ticketDetailRepository.save(ticketDetailEntity);
    return (TicketDetailDTO) genericConverter.toDTO(ticketDetailEntity, TicketDetailDTO.class);
  }

  @Override
  public void changeStatus(Long ids) {
    TicketDetailEntity ticketDetailEntity = ticketDetailRepository.findOneById(ids);
    if (ticketDetailEntity.getStatus() == true) {
      ticketDetailEntity.setStatus(false);
    } else {
      ticketDetailEntity.setStatus(true);
    }
    ticketDetailRepository.save(ticketDetailEntity);
  }


  @Override
  public int totalItem() {
    return (int) ticketDetailRepository.count();
  }

  @Override
  public List<TicketDetailDTO> findAll(Pageable pageable) {
    // Implement this method based on your requirements
    return null;
  }
}
