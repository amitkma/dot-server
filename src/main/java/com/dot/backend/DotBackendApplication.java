package com.dot.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DotBackendApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(DotBackendApplication.class, args);
    }
}
