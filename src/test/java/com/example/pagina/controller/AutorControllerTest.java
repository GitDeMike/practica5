package com.example.pagina.controller;

import com.example.pagina.model.Autor;
import com.example.pagina.service.AutorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AutorControllerTest {

    @InjectMocks
    private AutorController autorController;

    @Mock
    private AutorService autorService;

    private Autor autor;

    @BeforeEach
    public void setUp() {
        autor = new Autor("John Doe", "555-1234", "john.doe@example.com");
        autor.setId("1");
    }

    @Test
    public void getUserInfo_success() {
        when(autorService.getUserInfo(1)).thenReturn(Optional.of(autor));

        ResponseEntity<Object> response = autorController.getUserInfo(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(autor, response.getBody());
    }

    @Test
    public void getUserInfo_notFound() {
        when(autorService.getUserInfo(1)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = autorController.getUserInfo(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        // Additional assertions for the error response
    }

    @Test
    public void addUser_success() {
        when(autorService.addUser(autor)).thenReturn(autor);

        ResponseEntity<Object> response = autorController.addUser(autor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(autor, response.getBody());
    }

    @Test
    public void addUser_badRequest() {
        when(autorService.addUser(autor)).thenThrow(new IllegalArgumentException("Invalid input"));

        ResponseEntity<Object> response = autorController.addUser(autor);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid input", response.getBody());
    }

    @Test
public void addUser_internalServerError() {
    when(autorService.addUser(autor)).thenThrow(new RuntimeException("Unexpected error"));

    ResponseEntity<Object> response = autorController.addUser(autor);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
}
}
