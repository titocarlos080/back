package com.example.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.back.entity.Test;
/**
 *
 * @author machaca
 */
public interface TestRepository extends MongoRepository<Test, String>  {

}
