package com.ymlinks.boot.service;

import com.ymlinks.boot.model.User;

import java.util.List;

/**
 * Created by FuZhaohui on 2017/3/6.
 */
public interface UserService {

    List<User> getAll();

    List<User> getByKeyword(String keyword);
}
