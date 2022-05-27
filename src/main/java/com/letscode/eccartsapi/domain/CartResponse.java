package com.letscode.eccartsapi.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class CartResponse {

    private String id;
    private String userId;
    private Boolean isActiveStatus;
    private BigDecimal totalPrice;
    private Map<String, Long> products;

    public CartResponse(CartEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.isActiveStatus = entity.getIsActiveStatus();
        this.totalPrice = entity.getTotalPrice();
        this.products = entity.getProducts();
    }

}
