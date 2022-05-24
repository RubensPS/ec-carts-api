package com.letscode.eccartsapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartRequest {
    private String userId;

    public CartEntity toEntity() {
        return new CartEntity(
                this.getUserId()
        );
    }
}
