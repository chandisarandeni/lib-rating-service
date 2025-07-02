// User Guide:
// ***************************************************************************
// **** Please make sure to follow the steps below to run the application ****
// ***************************************************************************
// Database creation script: CREATE DATABASE lib_rating_db;

package com.sarasavi.lib_rating_service;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LibRatingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibRatingServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
