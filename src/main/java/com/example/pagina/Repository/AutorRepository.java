package com.example.pagina.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.pagina.model.Autor;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Integer>{
    

}