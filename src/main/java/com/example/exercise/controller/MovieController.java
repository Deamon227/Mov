package com.example.exercise.controller;

import com.example.exercise.exception.CustomNotFound;
import com.example.exercise.model.Category;
import com.example.exercise.model.Movie;
import com.example.exercise.service.category.ICategoryService;
import com.example.exercise.service.movie.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private IMovieService movieService;
    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("category")
    public Iterable<Category> listCategory() {
        return categoryService.findAll();
    }

    @GetMapping("")
    public ModelAndView index(@PageableDefault(size = 9, sort = {"name"})
                              @SortDefault.SortDefaults({@SortDefault(sort = "name", direction = Sort.Direction.ASC)})
                              Pageable pageable) {
        Page<Movie> movie = movieService.findAllPage(pageable);
        ModelAndView m = new ModelAndView("movie/index", "movies", movie);
        return m;
    }

    @GetMapping("/add")
    public ModelAndView showAdd() {
        ModelAndView m = new ModelAndView("movie/add", "movie", new Movie());
        return m;
    }

    @PostMapping("/add")
    public String add(Movie movie) {
        movieService.save(movie);
        return ("redirect:/movie");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id) throws CustomNotFound {
        Optional<Movie> movOp = movieService.findById(id);
            ModelAndView m = new ModelAndView("movie/update", "movie", movOp);
            return m;
    }

    @PostMapping("/edit/{id}")
    public String edit(Movie movie, @PathVariable Long id) {
        movie.setId(id);
        movieService.save(movie);
        return ("redirect:/movie");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        movieService.remove(id);
        return ("redirect:/movie");
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam Optional<String> search, @RequestParam Optional<Category> category, Pageable pageable) {
        Page<Movie> page;
        if (category.isPresent() && search.isPresent()) {
            page = movieService.searchByNameAndCategory(search.get(), category.get(), pageable);
        } else {
            page = movieService.findAllPage(pageable);
        }
        ModelAndView m = new ModelAndView("movie/index", "movies", page);
        m.addObject("search", search.get());
        return m;
    }

    @ExceptionHandler(CustomNotFound.class)
    public ModelAndView showNotFound(){
        ModelAndView m = new ModelAndView("category/notfound");
        return m;
    }
}
