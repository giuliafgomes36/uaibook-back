package com.backend.uaibook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class UaibookApplication {

    public static void main(String[] args) {
        SpringApplication.run(UaibookApplication.class, args);
    }

}
