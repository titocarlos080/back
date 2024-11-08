package com.example.back.entity;


import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Document(collection = "pagos")
public class Pago {

    @Id
    private String id;
    private String nombre;
    private Double monto;

    @DBRef
    private Test test;

    @DBRef
    private TipoPago tipoPago;

    // Getters and Setters
}
