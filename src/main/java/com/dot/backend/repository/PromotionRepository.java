package com.dot.backend.repository;

import com.dot.backend.domain.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PromotionRepository extends MongoRepository<Promotion, String> {
}
