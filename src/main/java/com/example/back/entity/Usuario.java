package com.example.back.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "usuario")
public class Usuario {
    @Id
    private String id;  // MongoDB's `_id`
    private String user;  // MongoDB uses `String` as the default `_id` type
    private String password;
    private String nombre;
    private String apellidos;
    private String sexo;
    private String fnac;
    private String telefono;
    private String correo;
    private String rolId;
    private String fotoPath;
    private String especialidad;
    private String token;

    

   
}
