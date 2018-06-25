package com.ymlinks.boot.repository;

import com.ymlinks.boot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, UserRepositoryAdvance {

}