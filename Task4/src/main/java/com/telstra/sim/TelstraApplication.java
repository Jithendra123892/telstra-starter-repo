package com.telstra.sim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
 class TelstraApplication {
   public static void main(String[] args) {
        SpringApplication.run(TelstraApplication.class, args);
    }

    @Bean
     RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
