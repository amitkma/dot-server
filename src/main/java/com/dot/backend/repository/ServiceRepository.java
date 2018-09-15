package com.dot.backend.repository;

import com.dot.backend.domain.Feed;
import com.dot.backend.domain.Service;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ServiceRepository extends MongoRepository<Service, String> {

    @Query("{ salon: ?0 }")
    List<Service> getServicesBySalon(String salon);

    @Query("{ salon: ?0, category: ?1}")
    List<Service> getServicesBySalonAndCategory(String salon, String category);
}
