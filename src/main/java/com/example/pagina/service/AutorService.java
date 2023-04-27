package com.example.pagina.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pagina.Repository.AutorRepository;
import com.example.pagina.model.Autor;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    
    public AutorService() {
    }

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Optional<Autor> getUserInfo(Integer userId) {
        return autorRepository.findById(userId);
    }

    public Autor addUser(Autor user) {
        try {
            checkIfPayloadIsValid(user);

            autorRepository.save(user);
            return user;
        } catch (Exception e) {
            throw e;
        }
    }


    public void checkIfPayloadIsValid(Autor user) {
        if (user == null) {
            throw new IllegalArgumentException("User is required");
        }
        if (user.getName() == null) {
            throw new IllegalArgumentException("Name is required");
        }
        if (user.getEmail() == null) {
            throw new IllegalArgumentException("Email is required");
        }
        if (user.getPhone() == null) {
            throw new IllegalArgumentException("Phone is required");
        }

        // check that email is valid with regex
        String email = user.getEmail();
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Email is invalid");
        }
    }
}