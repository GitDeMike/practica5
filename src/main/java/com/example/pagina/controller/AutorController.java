package com.example.pagina.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.pagina.model.Autor;
import com.example.pagina.service.AutorService;

// import org.slf4j.LoggerFactory;
// import ch.qos.logback.classic.Logger;

@RestController
public class AutorController {

    private AutorService autorService;

    // private static final Logger logger = (Logger) LoggerFactory.getLogger(AutorController.class);

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping("/contacto/{userID}")
    @CrossOrigin(origins = "*")
    public Autor getUserInfo(@PathVariable("userID") Integer userID) {
        return autorService.getUserInfo(userID);
    }

    @GetMapping("/contacto")
    @CrossOrigin(origins = "*")
    public void addUser(Autor autor){
        autorService.addUser(autor);
    }
}