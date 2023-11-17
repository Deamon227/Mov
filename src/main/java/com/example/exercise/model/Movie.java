package com.example.exercise.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String date;
    private String director;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Movie() {
    }

    public Movie(Long id, String name, String date, String director) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.director = director;
    }

    public Movie(Long id, String name, String date, String director, Category category) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.director = director;
        this.category = category;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
