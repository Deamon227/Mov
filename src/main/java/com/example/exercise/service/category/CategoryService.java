package com.example.exercise.service.category;

import com.example.exercise.exception.CustomNotFound;
import com.example.exercise.model.Category;
import com.example.exercise.model.DTO.ICountCategory;
import com.example.exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) throws CustomNotFound {
        Optional<Category> catOp = categoryRepository.findById(id);
        if(catOp.isPresent()){
            return catOp;
        }
        throw new CustomNotFound();
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<Category> findAllPage(Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<ICountCategory> countMovieNo() {
        return categoryRepository.countNumberCategory();
    }

    @Override
    public Iterable<Category> searchCategoriesByName1(String name) {
        return categoryRepository.searchCategoriesByName1(name);
    }
}
