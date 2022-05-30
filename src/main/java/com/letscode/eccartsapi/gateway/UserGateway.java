package com.letscode.eccartsapi.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGateway {

    private final UserFeignClient userFeignClient;

    public ResponseEntity<String> getUser(Integer userId) {
        return userFeignClient.getUser(userId);
        //String url = String.format("http://usersAPI:8080/users/user/%s", userId);
        //return restTemplate.getForEntity(url, String.class);
    }

}
