package com.dot.backend.repository;

import com.dot.backend.domain.PreInvite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PreInviteRepository extends MongoRepository<PreInvite, String> {
}
