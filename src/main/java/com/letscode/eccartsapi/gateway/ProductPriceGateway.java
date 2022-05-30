package com.letscode.eccartsapi.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ProductPriceGateway {

    private final ProductPriceFeignClient productPriceFeignClient;

    public ResponseEntity<BigDecimal> getPrice(String productId) {
        return productPriceFeignClient.getPrice(productId);
    }

}
