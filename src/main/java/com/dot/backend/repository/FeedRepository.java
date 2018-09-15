package com.dot.backend.repository;

import com.dot.backend.domain.Feed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FeedRepository extends MongoRepository<Feed, String> {

    @Query("{ tags: ?0}")
    List<Feed> getFeedsByTag(String tag);
}
