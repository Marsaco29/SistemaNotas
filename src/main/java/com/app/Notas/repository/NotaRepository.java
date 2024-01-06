package com.app.Notas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.Notas.entity.Nota;
import com.app.Notas.entity.Usuario;

public interface NotaRepository extends MongoRepository<Nota, String>{

    Usuario findByArchivada(boolean archivada);
}
