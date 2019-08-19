package com.strady.springsecurity.filter;

import com.strady.springsecurity.domain.User;
import com.strady.springsecurity.service.UserService;
import com.strady.springsecurity.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: strady
 * @Date: 2019-08-19
 * @Time: 13:58:47
 * @Description: token过滤器（解析和处理权限）
 */
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    //token请求头名称
    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //将ServletRequest转换为HttpServletRequest对象
        HttpServletRequest request = (HttpServletRequest) req;
        //取得请求头中的token
        String token = request.getHeader(tokenHeader);
        //取得token中的userName
        String userName = tokenUtils.getUsernameFromToken(token);

        //判断userName是否为空，且本次回话权限是否被写入
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //从数据库中查找用户
            User user = userService.findByUserName(userName);
            //检查token是否有效
            if (tokenUtils.validateToken(token, user)) {
                //生成通过认证
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //将权限写入
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(req, res);
    }
}
