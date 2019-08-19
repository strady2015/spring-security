package com.strady.springsecurity.service;

import com.strady.springsecurity.domain.User;

/**
 * @Author: strady
 * @Date: 2019-08-19
 * @Time: 13:41:00
 * @Description:
 */
public interface LoginService {

    User getLoginDetail(String username);

    String generateToken(User user);

}
