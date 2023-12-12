// TicketService.java
package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.TicketDTO;
import com.fap.cinanhalam.entity.TicketEntity;
import com.fap.cinanhalam.repository.TicketRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService implements IGenericService<TicketDTO> {

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private GenericConverter genericConverter;

  @Override
  public List<TicketDTO> findAll() {
    List<TicketDTO> result = new ArrayList<>();
    List<TicketEntity> entities = ticketRepository.findAll();

    for (TicketEntity entity : entities) {
      TicketDTO ticketDTO = (TicketDTO) genericConverter.toDTO(entity, TicketDTO.class);
      result.add(ticketDTO);
    }
    return result;
  }

  @Override
  public List<TicketDTO> findAllWithStatusIsTrue() {
    List<TicketDTO> results = new ArrayList<>();
    List<TicketEntity> entities = ticketRepository.findAllByStatusTrue();

    for (TicketEntity item : entities) {
      TicketDTO newDTO = (TicketDTO) genericConverter.toDTO(item, TicketDTO.class);
      results.add(newDTO);
    }
    return results;
  }

  @Override
  public TicketDTO save(TicketDTO ticketDTO) {
    TicketEntity ticketEntity = new TicketEntity();
    if (ticketDTO.getId() != null) {
      TicketEntity oldEntity = ticketRepository.findOneById(ticketDTO.getId());
      ticketEntity = (TicketEntity) genericConverter.updateEntity(ticketDTO, oldEntity);
    } else {
      ticketEntity = (TicketEntity) genericConverter.toEntity(ticketDTO, TicketEntity.class);
    }
    ticketRepository.save(ticketEntity);
    return (TicketDTO) genericConverter.toDTO(ticketEntity, TicketDTO.class);
  }

  @Override
  public void changeStatus(Long ids) {
    TicketEntity ticketEntity = ticketRepository.findOneById(ids);
    if (ticketEntity.getStatus() == null || !ticketEntity.getStatus()) {
      ticketEntity.setStatus(true);
    } else {
      ticketEntity.setStatus(false);
    }
    ticketRepository.save(ticketEntity);
  }

  @Override
  public int totalItem() {
    return (int) ticketRepository.count();
  }

  @Override
  public List<TicketDTO> findAll(Pageable pageable) {
    // Implement this method based on your requirements
    return null;
  }
}
