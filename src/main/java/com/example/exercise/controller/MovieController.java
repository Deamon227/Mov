package com.example.exercise.controller;

import com.example.exercise.model.Category;
import com.example.exercise.model.Movie;
import com.example.exercise.service.category.ICategoryService;
import com.example.exercise.service.movie.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private IMovieService movieService;
    @Autowired
    private ICategoryService categoryService;
    @ModelAttribute("category")
    public Iterable<Category> listCategory(){
        return categoryService.findAll();
    }
    @GetMapping("/home")
    public ModelAndView index(){
        ModelAndView m = new ModelAndView("movie/index", "movies", movieService.findAll());
        return m;
    }

    @GetMapping("/add")
    public ModelAndView showAdd(){
        ModelAndView m = new ModelAndView("movie/add", "movie", new Movie());
        return m;
    }
    @PostMapping("/add")
    public String add(Movie movie){
        movieService.save(movie);
        return ("redirect:/movie/home");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
        ModelAndView m = new ModelAndView("movie/update", "movie", movieService.findById(id));
        return m;
    }
    @PostMapping("/edit/{id}")
    public String edit(Movie movie, @PathVariable Long id){
        movie.setId(id);
        movieService.save(movie);
        return ("redirect:/movie/home");
    }

    @GetMapping("/delete")
    public String delete(@PathVariable Long id){
        movieService.remove(id);
        return ("redirect:/movie/home");
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam String search, Category category){
        Iterable<Movie> movie = movieService.searchByNameAndCategory(search, category);
        ModelAndView m = new ModelAndView("movie/index", "movies", movie);
        m.addObject("search", search);
        return m;
    }
}
