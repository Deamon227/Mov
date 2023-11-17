package com.example.exercise.service.movie;

import com.example.exercise.model.Category;
import com.example.exercise.model.Movie;
import com.example.exercise.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MovieService implements IMovieService{
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public Iterable<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Iterable<Movie> searchByNameAndCategory(String name, Category category) {
        return movieRepository.findByNameContainingAndCategory(name, category);
    }

    @Override
    public Iterable<Movie> getMovieByCategoryId(Long id) {
        return movieRepository.findAllByCategory_Id(id);
    }
}
