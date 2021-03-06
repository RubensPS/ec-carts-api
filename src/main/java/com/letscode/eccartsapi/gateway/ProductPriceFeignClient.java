package com.letscode.eccartsapi.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "ec-products-service")
public interface ProductPriceFeignClient {

    @GetMapping("/products/get/price/{productId}")
    ResponseEntity<BigDecimal> getPrice(@PathVariable String productId);

}
