package com.fap.cinanhalam.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGenericService<T> {
    List<T> findAll();

    List<T> findAllWithStatusIsTrue();
    T save(T DTO);

    void changeStatus(Long ids);
    int totalItem();

    List<T> findAll(Pageable pageable);
}
