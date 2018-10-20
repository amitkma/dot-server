package com.dot.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;

import java.io.IOException;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
public class DotBackendApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(DotBackendApplication.class, args);
    }
}
