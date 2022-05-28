package com.letscode.eccartsapi.gateway;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class ProductPriceGateway {
    private final RestTemplate restTemplate;

    public ProductPriceGateway(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<BigDecimal> getPrice(String productId) {
        String url = String.format("http://productsAPI:8082/products/get/price/%s", productId);
        return restTemplate.getForEntity(url, BigDecimal.class);
    }

}
