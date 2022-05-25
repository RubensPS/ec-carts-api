package com.letscode.eccartsapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartRequest {

    private String userId;

    public CartEntity toEntity() {
        return new CartEntity(
                this.getUserId()
        );
    }

    public CartRequest(String userId) {
        this.userId = userId;
    }
}
