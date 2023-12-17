package com.example.exercise.controller;

import com.example.exercise.model.Category;
import com.example.exercise.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class RESTCategoryController {
    @Autowired
    public CategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<Iterable<Category>> index(){
        Iterable<Category> c = categoryService.findAll();
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
}
