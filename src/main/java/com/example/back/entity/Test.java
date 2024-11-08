package com.example.back.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
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
    private Date fecha;
    private Date fechaEntrega;
    private String estado;
    private String observaciones;
    private Double calificacion;

    @DBRef
    private Usuario usuario;

    @DBRef
    private Categoria categoria;

    @DBRef
    private Resultado resultado;

    @DBRef
    private List<Pago> pagos;

    // Getters and Setters
}
