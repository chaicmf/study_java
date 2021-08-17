package com.cmf.security;

import javafx.event.EventType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        //增加操作日志
       // sysLogService.createLog(BusinessType.loginOp.toString(), EventType.platform_login.toString(),"登录失败","", Loglevel.error.toString(),ip,source);
        out.println("{\"code\":\"0\",\"url\":\"\"}");
    }
}
