package com.example.pagina.service;

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

    public Autor getUserInfo(Integer userId) {
        return autorRepository.findById(userId).get();
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
        if (user.getMail() == null) {
            throw new IllegalArgumentException("Email is required");
        }
        if (user.getPhone() == null) {
            throw new IllegalArgumentException("Phone is required");
        }

        // check that email is valid with regex
        String email = user.getMail();
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Email is invalid");
        }
    }
}