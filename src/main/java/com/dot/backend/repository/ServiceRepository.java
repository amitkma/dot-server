package com.dot.backend.repository;

import com.dot.backend.domain.Service;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ServiceRepository extends MongoRepository<Service, String> {

    List<Service> findBySalon(String salon);

    @Query(value = "{'category.$id': ?1}")
    List<Service> findBySalonAndCategory(String salon, ObjectId category);
}
