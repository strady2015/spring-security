package com.strady.springsecurity.service;

import com.strady.springsecurity.domain.User;

/**
 * @Author: strady
 * @Date: 2019-08-19
 * @Time: 13:47:29
 * @Description:
 */
public interface UserService {

    User findByUserName(String userName);
}
