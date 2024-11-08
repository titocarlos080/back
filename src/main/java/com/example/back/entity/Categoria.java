package com.example.back.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document(collection = "categorias")
public class Categoria {

    @Id
    private String id;
    private String nombre;

    // Getters and Setters
}

