package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findOneById();

    List<UserEntity> findAllByStatusTrue();
}
