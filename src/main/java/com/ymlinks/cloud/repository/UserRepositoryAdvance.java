package com.ymlinks.cloud.repository;

import com.ymlinks.cloud.model.User;

import java.util.List;

/**
 * Created by FuZhaohui on 2017/3/6.
 */
public interface UserRepositoryAdvance {
    List<User> findByKeyword(String keyword, int page);
}
