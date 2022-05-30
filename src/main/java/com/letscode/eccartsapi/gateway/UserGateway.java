package com.letscode.eccartsapi.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGateway {

    private final UserFeignClient userFeignClient;

    public ResponseEntity<String> getUser(String userId) {
        return userFeignClient.getUser(userId);
    }

}
