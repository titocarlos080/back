package com.example.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.back.entity.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {
}
