package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.UserRoleDTO;
import com.fap.cinanhalam.entity.RoleEntity;
import com.fap.cinanhalam.entity.UserEntity;
import com.fap.cinanhalam.entity.UserRoleEntity;
import com.fap.cinanhalam.repository.RoleRepository;
import com.fap.cinanhalam.repository.UserRepository;
import com.fap.cinanhalam.repository.UserRoleRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleService implements IGenericService<UserRoleDTO> {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private GenericConverter genericConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserRoleDTO> findAll() {
        List<UserRoleDTO> result = new ArrayList<>();
        List<UserRoleEntity> entities = userRoleRepository.findAll();

        for (UserRoleEntity entity : entities) {
            UserRoleDTO userRoleDTO = (UserRoleDTO) genericConverter.toDTO(entity, UserRoleDTO.class);
            result.add(userRoleDTO);
        }
        return result;
    }

    @Override
    public List<UserRoleDTO> findAllWithStatusIsTrue() {
        List<UserRoleDTO> results = new ArrayList<>();
        List<UserRoleEntity> entities = userRoleRepository.findAllByStatusTrue();

        for (UserRoleEntity item : entities) {
            UserRoleDTO newDTO = (UserRoleDTO) genericConverter.toDTO(item, UserRoleDTO.class);
            results.add(newDTO);
        }
        return results;
    }

    @Override
    public UserRoleDTO save(UserRoleDTO userRoleDTO) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();

        if (userRoleDTO.getId() != null) {
            UserRoleEntity oldEntity = userRoleRepository.getReferenceById(userRoleDTO.getId());
            userRoleEntity = (UserRoleEntity) genericConverter.updateEntity(userRoleDTO, oldEntity);
        } else {
            Long userId = userRoleDTO.getUserId();
            Long roleId = userRoleDTO.getRoleId();

            UserEntity userEntity = userRepository.findOneById(userId);
            RoleEntity roleEntity = roleRepository.findOneById(roleId);

            if(userEntity != null && roleEntity != null){
                userRoleEntity = (UserRoleEntity) genericConverter.toEntity(userRoleDTO, UserRoleEntity.class);

                userRoleEntity.setUser(userEntity);
                userRoleEntity.setRole(roleEntity);
            }
        }
        userRoleRepository.save(userRoleEntity);
//        UserRoleDTO result = (UserRoleDTO) genericConverter.toDTO(userRoleEntity, UserRoleDTO.class);
//        result.setName(roleEntity.getName());
        return (UserRoleDTO) genericConverter.toDTO(userRoleEntity, UserRoleDTO.class);
    }

//    @Override
//    public UserRoleDTO save(UserRoleDTO userRoleDTO) {
//        UserRoleEntity userRoleEntity = new UserRoleEntity();
//        if (userRoleDTO.getId() != null) {
//            UserRoleEntity oldEntity = userRoleRepository.getReferenceById(userRoleDTO.getId());
//            userRoleEntity = (UserRoleEntity) genericConverter.updateEntity(userRoleDTO, oldEntity);
//        } else {
//            Long userId = user
//            userRoleEntity = (UserRoleEntity) genericConverter.toEntity(userRoleDTO, UserRoleEntity.class);
//        }
//        userRoleRepository.save(userRoleEntity);
//        return (UserRoleDTO) genericConverter.toDTO(userRoleEntity, UserRoleDTO.class);
//    }

    @Override
    public void changeStatus(Long ids) {
        UserRoleEntity userRoleEntity = userRoleRepository.findOneById(ids);
        if (userRoleEntity.getStatus() == null || !userRoleEntity.getStatus()) {
            userRoleEntity.setStatus(true);
        } else {
            userRoleEntity.setStatus(false);
        }
        userRoleRepository.save(userRoleEntity);
    }

    @Override
    public int totalItem() {
        return (int) userRoleRepository.count();
    }

    @Override
    public List<UserRoleDTO> findAll(Pageable pageable) {
        // Implement this method based on your requirements
        return null;
    }
}
