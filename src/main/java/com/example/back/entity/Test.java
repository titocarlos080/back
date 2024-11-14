package com.example.back.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "test")
public class Test {
    @Id
    private String id;  // MongoDB default ID type
    private String nombre;
    private String fecha;
     private String estado;
    private String observaciones;
    private Integer calificacion;
    private String usuarioId;
    private String categoriaId;

     // Getters and Setters
}
