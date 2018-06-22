package com.ymlinks.cloud.repository;

import com.ymlinks.cloud.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, UserRepositoryAdvance {

}