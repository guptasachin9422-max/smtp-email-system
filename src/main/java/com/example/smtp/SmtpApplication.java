package com.example.smtp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmtpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmtpApplication.class, args);
    }

}