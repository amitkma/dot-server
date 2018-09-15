package com.dot.backend.repository;

import com.dot.backend.domain.Salon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalonRepository extends MongoRepository<Salon, String> {
}
