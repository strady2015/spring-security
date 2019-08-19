package com.strady.springsecurity.service.impl;

import com.strady.springsecurity.domain.User;
import com.strady.springsecurity.repository.UserRepository;
import com.strady.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: strady
 * @Date: 2019-08-19
 * @Time: 13:48:06
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserNameAndIsDeletedIsFalse(userName);
    }
}
