package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.UserRoleEntity;
import com.fap.cinanhalam.entity.VoucherUsageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    List<UserRoleEntity> findAllByStatusTrue();

    UserRoleEntity findOneById(Long id);

    @Query(value = "SELECT * FROM user_role WHERE user_id = :userId", nativeQuery = true)
    List<UserRoleEntity> findRoleIdsByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT role_id FROM user_role WHERE user_id = :userId", nativeQuery = true)
    List<Long> findRoleIdsByUserId2(@Param("userId") Long userId);
}
