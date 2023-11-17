package com.example.exercise.service.movie;

import com.example.exercise.model.Category;
import com.example.exercise.model.Movie;
import com.example.exercise.service.IGeneratedService;

public interface IMovieService extends IGeneratedService<Movie> {
    public Iterable<Movie> searchByNameAndCategory(String name, Category category);
    public Iterable<Movie> getMovieByCategoryId(Long id);
}
