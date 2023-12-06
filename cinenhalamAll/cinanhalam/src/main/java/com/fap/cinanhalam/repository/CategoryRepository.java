package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findOneById(long id);

    List<CategoryEntity> findAllByStatusTrue();

    @Query("SELECT c FROM CategoryEntity c WHERE c.name = :name and c.status = true")
    CategoryEntity findOneByNameAndStatusTrue(@Param("name") String name);

    @Query("SELECT c FROM CategoryEntity c WHERE c.id = :id and c.status = true")
    CategoryEntity findOneByIdAndStatusTrue(@Param("id") Long id);
}
