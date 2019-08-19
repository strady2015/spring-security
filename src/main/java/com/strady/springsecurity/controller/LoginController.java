package com.strady.springsecurity.controller;

import com.strady.springsecurity.domain.User;
import com.strady.springsecurity.domain.vo.LoginUser;
import com.strady.springsecurity.service.LoginService;
import com.strady.springsecurity.utils.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @Author: strady
 * @Date: 2019-08-19
 * @Time: 14:10:22
 * @Description:
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Value("${token.header}")
    private String tokenHeader;

    @GetMapping(value = "/")
    public String loginPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public String login(@Valid LoginUser requestLoginUser, BindingResult bindingResult) {
        // 检查有没有输入用户名密码和格式对不对
        if (bindingResult.hasErrors()) {
            return WebResult.CODE(0).msg("缺少参数或者参数格式不对").toJSON();
        }

        User loginDetail = loginService.getLoginDetail(requestLoginUser.getUserName());
        String ifLoginFail = checkAccount(requestLoginUser, loginDetail);
        if (ifLoginFail != null) {
            return ifLoginFail;
        }

        return WebResult.SUCCESS().msg("登录成功").attr(tokenHeader, loginService.generateToken(loginDetail)).toJSON();
    }

    private String checkAccount(LoginUser requestLoginUser, User user) {
        if (user == null) {
            return WebResult.CODE(434).msg("账号不存在!").toJSON();
        } else {
            if (!user.getPassword().equals(requestLoginUser.getPassword())) {
                return WebResult.CODE(438).msg("密码错误!").toJSON();
            }
        }
        return null;
    }

}
