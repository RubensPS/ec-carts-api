package com.letscode.eccartsapi.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "ec-users-service")
public interface UserFeignClient {

    @GetMapping("/users/user/{userId}")
    ResponseEntity<String> getUser(@PathVariable Integer userId);

}
