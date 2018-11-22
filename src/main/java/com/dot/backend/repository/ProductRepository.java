package com.dot.backend.repository;

import com.dot.backend.domain.Product;
import com.dot.backend.domain.Service;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    @Query(value = "{'category.$id': ?1}")
    List<Product> findByCategory(ObjectId category);
}
