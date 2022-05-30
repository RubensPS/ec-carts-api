package com.letscode.eccartsapi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductRequest {

    private String userId;
    private String productId;
    private Long quantity;

}
