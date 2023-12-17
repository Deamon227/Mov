package com.example.exercise.service.movie;

import com.example.exercise.model.Category;
import com.example.exercise.model.Movie;
import com.example.exercise.service.IGeneratedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMovieService extends IGeneratedService<Movie> {
    public Page<Movie> searchByNameAndCategory(String name, Category category, Pageable pageable);
    public Iterable<Movie> getMovieByCategoryId(Long id);
    public Iterable<Movie> searchMovieByName1(String name);
}
