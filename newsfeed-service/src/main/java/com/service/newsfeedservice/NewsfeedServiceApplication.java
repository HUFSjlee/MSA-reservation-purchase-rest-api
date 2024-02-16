package com.service.newsfeedservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NewsfeedServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsfeedServiceApplication.class, args);
    }

}
