package com.ymlinks.cloud.service.impl;

import com.ymlinks.cloud.model.User;
import com.ymlinks.cloud.repository.UserRepository;
import com.ymlinks.cloud.service.UserService;
import com.ymlinks.cloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FuZhaohui on 2017/3/6.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getAll() {
        Pageable page = new PageRequest(0, 10);
        return userRepository.findAll(page).getContent();
    }

    @Override
    public List<User> getByKeyword(String keyword) {
        return userRepository.findByKeyword(keyword, 1);
    }
}