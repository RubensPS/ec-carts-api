package com.letscode.eccartsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EcCartsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcCartsApiApplication.class, args);
    }

}
