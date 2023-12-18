package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.UserDTO;
import com.fap.cinanhalam.dto.UserRoleDTO;
import com.fap.cinanhalam.entity.UserEntity;
import com.fap.cinanhalam.entity.UserRoleEntity;
import com.fap.cinanhalam.repository.UserRepository;
import com.fap.cinanhalam.repository.UserRoleRepository;
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

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserRoleRepository userRoleRepository;

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
//        return null;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        if (userDTO.getId() != null) {
            UserEntity oldEntity = userRepository.findOneById(userDTO.getId());
            userEntity = (UserEntity) genericConverter.updateEntity(userDTO, oldEntity);
        } else {
            userEntity = (UserEntity) genericConverter.toEntity(userDTO, UserEntity.class);
            // Lưu userEntity để có ID
            userEntity = userRepository.save(userEntity);
        }

//        Long userId = userEntity.getId();
//        List<UserRoleEntity> userRoleEntities = userRoleRepository.findRoleIdsByUserId(userId);
//        userEntity.setRoleId(userRoleEntities);

        Long userId = userEntity.getId();
        List<UserRoleEntity> userRoleEntities = userRoleRepository.findRoleIdsByUserId(userId);
        userEntity.setRoleId(userRoleEntities);

        userEntity = userRepository.save(userEntity);

        List<Long> roleIds = userDTO.getRoleId();
        if (roleIds != null && !roleIds.isEmpty()) {
            for (Long roleId : roleIds) {
                UserRoleDTO userRoleDTO = new UserRoleDTO();
                userRoleDTO.setUserId(userEntity.getId());
                userRoleDTO.setRoleId(roleId);
                userRoleService.save(userRoleDTO);
            }
        }

        return (UserDTO) genericConverter.toDTO(userEntity, UserDTO.class);
    }


    @Override
    public void changeStatus(Long ids) {
        UserEntity userEntity = userRepository.findOneById(ids);
        if (userEntity.getStatus() == true) {
            userEntity.setStatus(false);
        } else {
            userEntity.setStatus(true);
        }
        userRepository.save(userEntity);
    }

    @Override
    public int totalItem() {
        return (int) userRepository.count();
    }

    @Override
    public List<UserDTO> findAll(Pageable pageable) {
        return null;
    }
}
