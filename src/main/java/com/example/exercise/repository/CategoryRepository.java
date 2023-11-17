package com.example.exercise.repository;

import com.example.exercise.model.Category;
import com.example.exercise.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
