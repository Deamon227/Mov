package com.example.exercise.service;

import com.example.exercise.exception.CustomNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface IGeneratedService<T> {
    Iterable<T> findAll();

    void save(T t);

    Optional<T> findById(Long id) throws CustomNotFound;

    void remove(Long id);

    Page<T> findAllPage(Pageable pageable);
}
