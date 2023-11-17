package com.example.exercise.repository;

import com.example.exercise.model.Category;
import com.example.exercise.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
    public Iterable<Movie> findByNameContainingAndCategory(String name, Category category);
    public Iterable<Movie> findAllByCategory_Id(Long id);
}
