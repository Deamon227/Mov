package com.example.exercise.service.category;

import com.example.exercise.model.Category;
import com.example.exercise.model.DTO.ICountCategory;
import com.example.exercise.service.IGeneratedService;

import java.util.Optional;

public interface ICategoryService extends IGeneratedService<Category> {
    Iterable<ICountCategory> countMovieNo();
    Iterable<Category> searchCategoriesByName1(String name);
}
