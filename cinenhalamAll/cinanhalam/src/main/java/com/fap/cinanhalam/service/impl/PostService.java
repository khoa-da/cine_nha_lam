package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.PostDTO;
import com.fap.cinanhalam.entity.PostEntity;
import com.fap.cinanhalam.repository.PostRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IGenericService<PostDTO> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    GenericConverter genericConverter;

    @Override
    public List<PostDTO> findAll() {
        List<PostDTO> result = new ArrayList<>();
        List<PostEntity> entities = postRepository.findAll();

        for (PostEntity entity: entities
             ) {
            PostDTO postDTO = (PostDTO) genericConverter.toDTO(entity, PostDTO.class);
            result.add(postDTO);
        }
        return result;
    }

    @Override
    public List<PostDTO> findAllWithStatusIsTrue() {
        List<PostDTO> results = new ArrayList();
        List<PostEntity> entities = postRepository.findAllByStatusTrue();

        for(PostEntity item : entities) {
            PostDTO newDTO = (PostDTO) genericConverter.toDTO(item,PostDTO.class);
            results.add(newDTO);
        }
        return results;
    }

    @Override
    public PostDTO save(PostDTO postDTO) {
        PostEntity postEntity = new PostEntity();
        if (postDTO.getId() != null) {
            PostEntity oldEntity = postRepository.findOneById(postDTO.getId());
            postEntity = (PostEntity) genericConverter.updateEntity(postDTO, oldEntity);
        } else {
            postEntity = (PostEntity) genericConverter.toEntity(postDTO, PostEntity.class);
        }
        postRepository.save(postEntity);
        return (PostDTO) genericConverter.toDTO(postEntity, PostDTO.class);
    }

    @Override
    public void changeStatus(Long ids) {
        PostEntity postEntity = postRepository.findOneById(ids);
        if (postEntity.getStatus() == true) {
            postEntity.setStatus(false);
        } else {
            postEntity.setStatus(true);
        }
        postRepository.save(postEntity);
    }

    @Override
    public int totalItem() {
        return (int)postRepository.count();
    }

    @Override
    public List<PostDTO> findAll(Pageable pageable) {
        return null;
    }
}
