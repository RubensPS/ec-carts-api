package com.letscode.eccartsapi.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashMap;

@Document(collection = "carts")
@Getter
@Setter
public class CartEntity {

    @Id
    private String id;
    private String userId;
    private Boolean isActiveStatus;

    @Field
    private HashMap<String, Long> products;

    public CartEntity(String userId) {
        this.userId = userId;
        this.isActiveStatus = true;
        this.products = new HashMap<>();
    }

}
