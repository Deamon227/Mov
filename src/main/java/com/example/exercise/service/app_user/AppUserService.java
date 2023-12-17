package com.example.exercise.service.app_user;

import com.example.exercise.exception.CustomNotFound;
import com.example.exercise.model.AppUser;
import com.example.exercise.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AppUserService implements IAppUserService{
    @Autowired
    public AppUserRepository appUserRepository;
    @Override
    public Iterable<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public void save(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public Optional<AppUser> findById(Long id) throws CustomNotFound {
        return appUserRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public Page<AppUser> findAllPage(Pageable pageable) {
        return null;
    }
}
