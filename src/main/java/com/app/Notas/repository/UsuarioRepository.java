package com.app.Notas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.Notas.entity.Usuario;


public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	
	Usuario findByUserAndPassword(String user, String password);
}
