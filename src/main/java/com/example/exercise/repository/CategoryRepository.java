package com.example.exercise.repository;

import com.example.exercise.model.Category;
import com.example.exercise.model.DTO.ICountCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query(nativeQuery = true, value = "select category.name, count(movie.id) as number from movie join category on movie.category_id = category.id group by category.name")
    Iterable<ICountCategory> countNumberCategory() ;
    @Query(nativeQuery = true, value = "select * from category where category.name like %:name%")
    Iterable<Category> searchCategoriesByName1(@Param("name") String name);
}
