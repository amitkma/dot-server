package com.dot.backend.resources;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SalonRepository extends MongoRepository<Salon, String> {
}
