package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.models.Transport;

public interface TransportRepository extends MongoRepository<Transport, String>{
    List<Transport> findByCategory(String category);

    Transport findByLicense(String license);
}
