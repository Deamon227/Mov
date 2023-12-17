package com.example.exercise.service.movie;

import com.example.exercise.exception.CustomNotFound;
import com.example.exercise.model.Category;
import com.example.exercise.model.Movie;
import com.example.exercise.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Optional<Movie> findById(Long id) throws CustomNotFound {
        Optional<Movie> movOp = movieRepository.findById(id);
        if(movOp.isPresent()){
            return movOp;
        }
        throw new CustomNotFound();
    }

    @Override
    public void remove(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Page<Movie> findAllPage(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    public Page<Movie> searchByNameAndCategory(String name, Category category, Pageable pageable) {
        return movieRepository.findByNameContainingAndCategory(name, category, pageable);
    }

    @Override
    public Iterable<Movie> getMovieByCategoryId(Long id) {
        return movieRepository.findAllByCategory_Id(id);
    }

    @Override
    public Iterable<Movie> searchMovieByName1(String name) {
        return movieRepository.searchMovieByName1(name);
    }
}
