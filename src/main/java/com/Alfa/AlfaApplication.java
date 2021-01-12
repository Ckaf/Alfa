package com.Alfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.Alfa.controllers"})
public class AlfaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlfaApplication.class, args);
    }
}
