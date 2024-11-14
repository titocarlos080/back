

package com.example.back.entity;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "resultados")
public class Resultado {

    @Id
    private String id;
    private String resultado;
    private Date fecha;
    private String observaciones;
    private String interpretacion;
    private String detalles;
    private String urlImagenPath;
    private String TestId;

   

    // Getters and Setters
}

