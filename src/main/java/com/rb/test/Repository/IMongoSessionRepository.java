package com.rb.test.Repository;

import com.rb.test.Models.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMongoSessionRepository extends MongoRepository<Session, String> {
}
