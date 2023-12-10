package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.PostEntity;
import com.fap.cinanhalam.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findOneById(long id);

    List<RoleEntity> findAllByStatusTrue();
}
