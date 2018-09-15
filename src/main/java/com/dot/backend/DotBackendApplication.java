package com.dot.backend;

import com.dot.backend.config.JwtAuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class DotBackendApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(DotBackendApplication.class, args);
    }
}
