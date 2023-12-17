package com.example.exercise.controller;

import com.example.exercise.exception.CustomNotFound;
import com.example.exercise.model.AppUser;
import com.example.exercise.model.Category;
import com.example.exercise.service.app_user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/appuser")
public class AppUserController {
    @Autowired
    public AppUserService appUserService;
    @GetMapping("")
    public ResponseEntity<Iterable<AppUser>> index(){
        Iterable<AppUser> a = appUserService.findAll();
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUser> save(@RequestBody AppUser appUser){
        appUserService.save(appUser);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppUser> remove(@PathVariable Long id){
        appUserService.remove(id);
        return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> edit(@RequestBody AppUser appUser,@PathVariable Long id) throws CustomNotFound {
        Optional<AppUser> a = appUserService.findById(id);
        if (a.isPresent()){
            appUser.setId(id);
            appUserService.save(appUser);
            return new ResponseEntity<>(appUser, HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
