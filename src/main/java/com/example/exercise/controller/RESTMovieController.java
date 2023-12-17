package com.example.exercise.controller;

import com.example.exercise.exception.CustomNotFound;
import com.example.exercise.model.Category;
import com.example.exercise.model.Movie;
import com.example.exercise.service.category.CategoryService;
import com.example.exercise.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/movie")
public class RESTMovieController {
    @Autowired
    public MovieService movieService;

    @GetMapping("")
    public ResponseEntity<Iterable<Movie>> index(){
        Iterable<Movie> m = movieService.findAll();
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Movie> add(@RequestBody Movie movie){
        movieService.save(movie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteById(@PathVariable Long id){
        movieService.remove(id);
        return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> edit(@RequestBody Movie movie ,@PathVariable Long id) throws CustomNotFound {
        Optional<Movie> m = movieService.findById(id);
        if (m.isPresent()){
            movie.setId(id);
            movieService.save(movie);
            return new ResponseEntity<>(movie, HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) throws CustomNotFound {
        Optional<Movie> m = movieService.findById(id);
        if (m.isPresent()){
            return new ResponseEntity<>(m.get(), HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Movie>> search(@RequestParam Optional<String> name) {
        Iterable<Movie> m;
        if (name.isPresent()) {
            m = movieService.searchMovieByName1(name.get());
            Integer size =  ((Collection<?>) m).size();
            if (size!=0){
                return new ResponseEntity<>(m,HttpStatus.FOUND);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<Iterable<Movie>> findByCategory(@RequestBody Category category){
        Iterable<Movie> movie = movieService.getMovieByCategoryId(category.getId());
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}
