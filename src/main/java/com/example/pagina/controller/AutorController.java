package com.example.pagina.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pagina.model.Autor;
import com.example.pagina.service.AutorService;


@RestController
public class AutorController {

    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping("/autor/{userID}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> getUserInfo(@PathVariable("userID") Integer userID) {
        Optional<Autor> autor = autorService.getUserInfo(userID);
        if (autor.isPresent()) {
            return ResponseEntity.ok(autor.get());
        } else {
            HttpStatus status = HttpStatus.NOT_FOUND;
            String message = "Autor not found";
            String path = "/contacto/" + userID;

            return ResponseEntity.status(status)
                    .body(createErrorResponse(status, message, path));
        }
    }

    private Map<String, Object> createErrorResponse(HttpStatus status, String message, String path) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now().toString());
        errorResponse.put("status", status.value());
        errorResponse.put("error", status.getReasonPhrase());
        errorResponse.put("message", message);
        errorResponse.put("path", path);
        return errorResponse;
    }

    @PostMapping("/autor")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> addUser(@RequestBody Autor autor) {
        try {
            Autor returnedAutor = autorService.addUser(autor);
            return ResponseEntity.ok(returnedAutor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            String message = "Unexpected error";
            String path = "/autor";

            return ResponseEntity.status(status)
                    .body(createErrorResponse(status, message, path));
        }
    }

    @PostMapping("/autor/update/{userID}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> updateUserInfo(@PathVariable("userID") Integer userID, @RequestBody Autor user) {
        try {
            Autor updatedUser = autorService.updateUser(userID, user);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            String message = "Unexpected error";
            String path = "/autor/update/" + userID;

            return ResponseEntity.status(status)
                    .body(createErrorResponse(status, message, path));
        }
    }

    @DeleteMapping("/autor/{userID}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> deleteUser(@PathVariable("userID") Integer userID) {
        try {
            autorService.deleteUser(userID);
            return ResponseEntity.status(HttpStatus.OK).body("Autor deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            String message = "Unexpected error";
            String path = "/autor/" + userID;

            return ResponseEntity.status(status)
                    .body(createErrorResponse(status, message, path));
        }
    }

}