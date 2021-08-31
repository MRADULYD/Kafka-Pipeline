package com.rb.test.Repository;

import com.rb.test.Models.Battle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMongoBattleRepository extends MongoRepository<Battle, String> {

}
