package com.example.exercise.controller;

import com.example.exercise.exception.CustomNotFound;
import com.example.exercise.model.Category;
import com.example.exercise.model.DTO.ICountCategory;
import com.example.exercise.service.category.ICategoryService;
import com.example.exercise.service.movie.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    public ICategoryService categoryService;
    @Autowired
    public IMovieService movieService;

    @GetMapping("")
    public ModelAndView index(){
        Iterable<Category> cat = categoryService.findAll();
        ModelAndView m = new ModelAndView("category/index", "categories", cat);
        return m;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable Long id) throws CustomNotFound {
        Optional<Category> cat = categoryService.findById(id);
            ModelAndView found = new ModelAndView("category/detail", "category", cat.get());
            found.addObject("movies", movieService.getMovieByCategoryId(id));
            return found;
    }

    @GetMapping("/add")
    public ModelAndView showAdd(){
        ModelAndView m = new ModelAndView("category/add", "category", new Category());
        return m;
    }
    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("category") Category category, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return "category/add";
        }else{
            categoryService.save(category);
            return ("redirect:/category");
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id) throws CustomNotFound {
        ModelAndView m = new ModelAndView("category/update", "category", categoryService.findById(id));
        return m;
    }
    @PostMapping("/edit/{id}")
    public String edit(Category category, @PathVariable Long id){
        category.setId(id);
        categoryService.save(category);
        return ("redirect:/category");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        categoryService.remove(id);
        return ("redirect:/category");
    }

    @GetMapping("/total")
    public ModelAndView getTotal(){
        Iterable<ICountCategory> count = categoryService.countMovieNo();
        ModelAndView m = new ModelAndView("category/total", "categories", count);
        return m;
    }

    @ExceptionHandler(CustomNotFound.class)
    public ModelAndView showNotFound(){
        ModelAndView m = new ModelAndView("category/notfound");
        return m;
    }
}
