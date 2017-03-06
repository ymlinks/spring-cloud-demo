package com.boka.cloud.repository;

import com.boka.cloud.model.User;

import java.util.List;

/**
 * Created by FuZhaohui on 2017/3/6.
 */
public interface UserRepositoryAdvance {
    List<User> findByKeyword(String keyword, int page);
}
