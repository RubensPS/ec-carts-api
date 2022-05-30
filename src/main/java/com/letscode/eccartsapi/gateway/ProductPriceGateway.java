package com.letscode.eccartsapi.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductPriceGateway {

    private final ProductPriceFeignClient productPriceFeignClient;

    public ResponseEntity<BigDecimal> getPrice(String productId) {
        return productPriceFeignClient.getPrice(productId);
        //String url = String.format("http://productsAPI:8082/products/get/price/%s", productId);
        //return restTemplate.getForEntity(url, BigDecimal.class);
    }

}
