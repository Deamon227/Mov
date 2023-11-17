package com.example.exercise.formatter;

import com.example.exercise.model.Category;
import com.example.exercise.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class MovieFormatter implements Formatter<Category> {
    private final ICategoryService categoryService;
    @Autowired
    public MovieFormatter(ICategoryService categoryService){
        this.categoryService =categoryService;
    }

    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        Optional<Category> category= categoryService.findById(Long.parseLong(text));
        return category.orElse(null);
    }

    @Override
    public String print(Category object, Locale locale) {
        return null;
    }
}
