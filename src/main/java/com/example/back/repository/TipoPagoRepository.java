package com.example.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.back.entity.TipoPago;

public interface TipoPagoRepository extends MongoRepository<TipoPago, String> {
}
