package com.example.exercise.controller;

import com.example.exercise.exception.CustomNotFound;
import com.example.exercise.model.Category;
import com.example.exercise.model.Movie;
import com.example.exercise.service.category.CategoryService;
import com.example.exercise.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class RESTCategoryController {
    @Autowired
    public CategoryService categoryService;
    @Autowired
    public MovieService movieService;
    @GetMapping("")
    public ResponseEntity<Iterable<Category>> index(){
        Iterable<Category> c = categoryService.findAll();
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Category> add(@RequestBody Category category){
        categoryService.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id){
        categoryService.remove(id);
        return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable Long id) throws CustomNotFound {
        Optional<Category> c = categoryService.findById(id);
        if (c.isPresent()){
            category.setId(id);
            categoryService.save(category);
            return new ResponseEntity<>(category, HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Category>> search(@RequestParam Optional<String> name){
        Iterable<Category> c;
        if (name.isPresent()) {
            c = categoryService.searchCategoriesByName1(name.get());
            Integer size =  ((Collection<?>) c).size();
            if (size != 0){
                return new ResponseEntity<>(c, HttpStatus.FOUND);
            }else {
                return new ResponseEntity<>(c, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
