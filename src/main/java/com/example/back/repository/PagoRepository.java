package com.example.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.back.entity.Pago;

public interface PagoRepository extends MongoRepository<Pago, String> {
}
