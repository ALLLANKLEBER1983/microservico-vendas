package com.br.pluralstudio.product.api.config.interceptor;

import com.br.pluralstudio.product.api.module.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handle) throws Exception{

        if(isOptions(request)){
            return true;
        }
        var authorization = request.getHeader(AUTHORIZATION);
        jwtService.validateAuthorization(authorization);
        return true;

    }

    private boolean isOptions(HttpServletRequest request){
        return HttpMethod.OPTIONS.name().equals(request.getMethod());
    }
}
