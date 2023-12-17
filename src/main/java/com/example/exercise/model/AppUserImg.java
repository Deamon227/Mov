package com.example.exercise.model;

import javax.persistence.*;

@Table(name = "app_user")
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pass;
    private String avatar;

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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public AppUser() {
    }

    public AppUser(String name, String pass, String avatar) {
        this.name = name;
        this.pass = pass;
        this.avatar = avatar;
    }

    public AppUser(Long id, String name, String pass, String avatar) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.avatar = avatar;
    }
}
