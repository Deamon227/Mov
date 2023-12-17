package com.example.exercise.repository;

import com.example.exercise.model.Category;
import com.example.exercise.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    public Page<Movie> findByNameContainingAndCategory(String name, Category category, Pageable pageable);
    public Iterable<Movie> findAllByCategory_Id(Long id);
    @Query(nativeQuery = true, value = "select * from movie where movie.name like %:name%")
    Iterable<Movie> searchMovieByName1(@Param("name") String name);
}
