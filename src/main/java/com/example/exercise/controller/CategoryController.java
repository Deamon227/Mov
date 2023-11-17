package com.example.exercise.controller;

import com.example.exercise.model.Category;
import com.example.exercise.model.Movie;
import com.example.exercise.service.category.ICategoryService;
import com.example.exercise.service.movie.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    public ICategoryService categoryService;
    @Autowired
    public IMovieService movieService;

    @GetMapping("/home")
    public ModelAndView index(){
        Iterable<Category> cat = categoryService.findAll();
        ModelAndView m = new ModelAndView("category/index", "categories", cat);
        return m;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable Long id){
        Optional<Category> cat = categoryService.findById(id);
        if (cat.isPresent()) {
            ModelAndView found = new ModelAndView("category/detail", "category", cat.get());
            found.addObject("movies", movieService.getMovieByCategoryId(id));
            return found;
        }else{
            ModelAndView notFound = new ModelAndView("category/notfound");
            return notFound;
        }
    }
}
