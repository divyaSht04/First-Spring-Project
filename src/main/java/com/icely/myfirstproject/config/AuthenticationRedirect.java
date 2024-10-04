package com.icely.myfirstproject.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Set;

public class AuthenticationRedirect implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        System.out.println(roles.contains("ADMIN"));

        if(roles.contains("ADMIN")) response.sendRedirect(request.getContextPath() + "/students");
        else response.sendRedirect(request.getContextPath() + "/StudentPage");
    }
}
