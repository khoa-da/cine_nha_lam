package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.UserDTO;
import com.fap.cinanhalam.entity.UserEntity;
import com.fap.cinanhalam.repository.UserRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IGenericService<UserDTO> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GenericConverter genericConverter;

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> result = new ArrayList<>();
        List<UserEntity> entities = userRepository.findAll();
        for (UserEntity entity: entities
             ) {
            UserDTO userDTO = (UserDTO) genericConverter.toDTO(entity, UserDTO.class);
            result.add(userDTO);
        }
        return result;
    }

    @Override
    public List<UserDTO> findAllWithStatusIsTrue() {
        List<UserDTO> result = new ArrayList<>();
        List<UserEntity> entities = userRepository.findAllByStatusTrue();
        for (UserEntity entity: entities
        ) {
            UserDTO userDTO = (UserDTO) genericConverter.toDTO(entity, UserDTO.class);
            result.add(userDTO);
        }
        return result;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return null;
    }

    @Override
    public void changeStatus(Long ids) {

    }

    @Override
    public int totalItem() {
        return 0;
    }

    @Override
    public List<UserDTO> findAll(Pageable pageable) {
        return null;
    }
}
