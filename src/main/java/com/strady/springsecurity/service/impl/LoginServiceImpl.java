package com.strady.springsecurity.service.impl;

import com.strady.springsecurity.domain.User;
import com.strady.springsecurity.repository.UserRepository;
import com.strady.springsecurity.service.LoginService;
import com.strady.springsecurity.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: strady
 * @Date: 2019-08-19
 * @Time: 13:45:07
 * @Description:
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public User getLoginDetail(String username) {
        return userRepository.findByUserNameAndIsDeletedIsFalse(username);
    }

    @Override
    public String generateToken(User user) {
        return tokenUtils.generateToken(user);
    }
}
