package com.fap.cinanhalam.repository;

import com.fap.cinanhalam.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    PostEntity findOneById(long id);

    List<PostEntity> findAllByStatusTrue();
}
