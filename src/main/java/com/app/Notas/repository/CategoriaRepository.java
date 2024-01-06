package com.app.Notas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.Notas.entity.Categoria;


public interface CategoriaRepository extends MongoRepository<Categoria, String> {

}
