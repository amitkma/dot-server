package com.dot.backend.config;

import com.dot.backend.security.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends GenericFilterBean {

    @Autowired
    Environment environment;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or wrong token type");
        } else {
            final String token = authHeader.substring(7);
            try {
                final Claims claims = TokenService.validateToken(token, environment.getProperty("SECRET_KEY"));
                request.setAttribute("claims", claims);
                chain.doFilter(req, res);
            } catch (final Exception e) {
                e.printStackTrace();
                if (e instanceof ServletException) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server error");
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                }
            }
        }

    }
}
