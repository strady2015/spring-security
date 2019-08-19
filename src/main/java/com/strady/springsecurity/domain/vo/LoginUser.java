package com.strady.springsecurity.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: strady
 * @Date: 2019-08-19
 * @Time: 13:38:48
 * @Description: 登录验证VO
 */
@Data
public class LoginUser {

    @NotNull
    private String userName;

    @NotNull
    private String password;
}