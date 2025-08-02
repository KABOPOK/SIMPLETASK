package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        System.out.println(Instant.now().toString());
        SpringApplication.run(DemoApplication.class, args);
    }

}
