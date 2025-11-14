package com.example.demo.security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDenyHandler implements AccessDeniedHandler {

    private String redirectUrl;
    public CustomAccessDenyHandler(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //권한이 없는 경우 처리할 코드
        response.sendRedirect(redirectUrl);
    }
}
