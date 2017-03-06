package com.boka.cloud.repository;

import com.boka.cloud.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, UserRepositoryAdvance {

}