

package com.example.back.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "tipopagos")
public class TipoPago {

    @Id
    private String id;
    private String tipo;
    private String nro;

    // Getters and Setters
}