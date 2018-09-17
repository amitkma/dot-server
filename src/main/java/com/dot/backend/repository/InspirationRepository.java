package com.dot.backend.repository;

import com.dot.backend.domain.Inspiration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface InspirationRepository extends MongoRepository<Inspiration, String> {

    @Query("{ category: ?0}")
    List<Inspiration> getInspirationByCategory(String category);
}
