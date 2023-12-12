package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.FeedbackDTO;
import com.fap.cinanhalam.entity.FeedbackEntity;
import com.fap.cinanhalam.repository.FeedbackRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService implements IGenericService<FeedbackDTO> {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    GenericConverter genericConverter;
    @Override
    public List<FeedbackDTO> findAll() {
        List<FeedbackDTO> result = new ArrayList<>();
        List<FeedbackEntity> entities = feedbackRepository.findAll();
        for (FeedbackEntity entity: entities
             ) {
            FeedbackDTO feedbackDTO = (FeedbackDTO) genericConverter.toDTO(entity, FeedbackDTO.class);
            result.add(feedbackDTO);
        }
        return result;
    }

    @Override
    public List<FeedbackDTO> findAllWithStatusIsTrue() {
        List<FeedbackDTO> result = new ArrayList<>();
        List<FeedbackEntity> entities = feedbackRepository.findAllByStatusTrue();
        for (FeedbackEntity entity: entities
        ) {
            FeedbackDTO feedbackDTO = (FeedbackDTO) genericConverter.toDTO(entity, FeedbackDTO.class);
            result.add(feedbackDTO);
        }
        return result;
    }

    @Override
    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        if(feedbackDTO.getId() != null){
            FeedbackEntity oldEntity = feedbackRepository.findOneById(feedbackDTO.getId());
            feedbackEntity = (FeedbackEntity) genericConverter.updateEntity(feedbackDTO, oldEntity);
        }else{
            feedbackEntity = (FeedbackEntity) genericConverter.toEntity(feedbackDTO, FeedbackEntity.class);
        }
        feedbackRepository.save(feedbackEntity);
        return (FeedbackDTO) genericConverter.toDTO(feedbackEntity, FeedbackDTO.class);
    }

    @Override
    public void changeStatus(Long ids) {
        FeedbackEntity feedbackEntity = feedbackRepository.findOneById(ids);
        if(feedbackEntity.getStatus() == true){
            feedbackEntity.setStatus(false);
        }else{
            feedbackEntity.setStatus(true);
        }
        feedbackRepository.save(feedbackEntity);
    }

    @Override
    public int totalItem() {
        return (int) feedbackRepository.count();
    }

    @Override
    public List<FeedbackDTO> findAll(Pageable pageable) {
        return null;
    }
}
