package com.letscode.eccartsapi.gateway;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserGateway {

    private final RestTemplate restTemplate;

    public UserGateway(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<String> getUser(Integer userId) {
        String url = String.format("http://usersAPI:8080/users/user/%s", userId);
        return restTemplate.getForEntity(url, String.class);
    }
}
