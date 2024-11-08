 

package com.example.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.back.entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}

