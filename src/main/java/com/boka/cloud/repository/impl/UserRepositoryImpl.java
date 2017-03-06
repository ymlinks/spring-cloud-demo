package com.boka.cloud.repository.impl;

import com.boka.cloud.model.User;
import com.boka.cloud.repository.UserRepositoryAdvance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by FuZhaohui on 2017/3/6.
 */
public class UserRepositoryImpl implements UserRepositoryAdvance {

    @Autowired
    private MongoOperations ops;

    @Override
    public List<User> findByKeyword(String keyword, int page) {
        Query query = new Query(Criteria.where("name").regex(keyword, "m").and("activatedStatus").gte(1));
        query.with(new PageRequest(page - 1, 10));
        return ops.find(query, User.class);
    }

}
