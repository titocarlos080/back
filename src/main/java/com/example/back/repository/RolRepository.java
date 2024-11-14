package com.example.back.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.back.entity.Rol;
@Repository
public interface  RolRepository extends   MongoRepository<Rol,String>{
    
}
