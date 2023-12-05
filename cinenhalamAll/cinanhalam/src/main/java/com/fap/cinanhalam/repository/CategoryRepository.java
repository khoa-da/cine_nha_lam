package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findOneById(long id);

    List<CategoryEntity> findAllByStatusTrue();
}
