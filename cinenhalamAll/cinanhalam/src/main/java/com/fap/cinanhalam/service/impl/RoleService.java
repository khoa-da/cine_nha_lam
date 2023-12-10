package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.RoleDTO;
import com.fap.cinanhalam.entity.RoleEntity;
import com.fap.cinanhalam.repository.RoleRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
@Service
public class RoleService implements IGenericService<RoleDTO> {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    GenericConverter genericConverter;

    @Override
    public List findAll() {
        List<RoleDTO> result = new ArrayList<>();
        List<RoleEntity> entities = roleRepository.findAll();

        for (RoleEntity entity : entities
             ) {
            RoleDTO roleDTO = (RoleDTO) genericConverter.toDTO(entity, RoleDTO.class);
            result.add(roleDTO);
        }
        return result;
    }

    @Override
    public List findAllWithStatusIsTrue() {
        List<RoleDTO> result = new ArrayList<>();
        List<RoleEntity> entities = roleRepository.findAllByStatusTrue();

        for (RoleEntity entity : entities
        ) {
            RoleDTO roleDTO = (RoleDTO) genericConverter.toDTO(entity, RoleDTO.class);
            result.add(roleDTO);
        }
        return result;
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        if(roleDTO.getId() != null){
            RoleEntity oldEntity = roleRepository.findOneById(roleDTO.getId());
            roleEntity = (RoleEntity) genericConverter.updateEntity(roleDTO, oldEntity);
        }else{
            roleEntity = (RoleEntity) genericConverter.toEntity(roleDTO, RoleEntity.class);
        }
        roleRepository.save(roleEntity);
        return (RoleDTO) genericConverter.toDTO(roleEntity, RoleDTO.class);
    }


    @Override
    public void changeStatus(Long ids) {
        RoleEntity roleEntity = roleRepository.findOneById(ids);
        if(roleEntity.getStatus() == true){
            roleEntity.setStatus(false);
        }else{
            roleEntity.setStatus(true);
        }
        roleRepository.save(roleEntity);
    }

    @Override
    public int totalItem() {
        return (int)roleRepository.count();
    }

    @Override
    public List findAll(Pageable pageable) {
        return null;
    }
}
