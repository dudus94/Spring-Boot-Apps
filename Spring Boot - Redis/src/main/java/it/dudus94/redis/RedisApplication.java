/*
 * Copyright 2021 fOrest94
 * All Rights Reserved
 */
package it.dudus94.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RedisApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
