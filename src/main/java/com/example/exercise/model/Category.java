package com.example.exercise.model;

import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "category")
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name can't be empty")
    @Size(min = 8, max = 20, message = "Category must be between 8 and 20 characters")
    @Pattern(regexp = "([C][0-9][0-9][ ][a-zA-Z])\\w+", message = "Category name must start with the format C, a number and a single word")
    private String name;
    @Size(min = 20, max = 150, message = "Description must be between 20 and 150 characters")
    private String description;
//    @OneToMany
//    private List<Movie> movie;

    public Category() {
    }

    public Category(Long id, @Size(min = 8, max = 20) String name, @Size(min = 20, max = 150) String description, List<Movie> movie) {
        this.id = id;
        this.name = name;
        this.description = description;
//        this.movie = movie;
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public List<Movie> getMovie() {
//        return movie;
//    }
//
//    public void setMovie(List<Movie> movie) {
//        this.movie = movie;
//    }
}
