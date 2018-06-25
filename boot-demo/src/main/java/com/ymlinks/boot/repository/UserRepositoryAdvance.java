package com.ymlinks.boot.repository;

import com.ymlinks.boot.model.User;

import java.util.List;

/**
 * Created by FuZhaohui on 2017/3/6.
 */
public interface UserRepositoryAdvance {
    List<User> findByKeyword(String keyword, int page);
}
