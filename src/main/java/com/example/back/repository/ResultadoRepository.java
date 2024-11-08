package com.example.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.back.entity.Resultado;
/**
 *
 * @author machaca
 */
public interface ResultadoRepository extends MongoRepository<Resultado, String>  {

}
