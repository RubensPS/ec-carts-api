package com.letscode.eccartsapi.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "carts")
@Getter
@Setter
public class CartEntity {

    @Id
    private String id;
    private String userId;
    private Boolean isActiveStatus;

    @Field
    private Map<String, Long> products = new HashMap<>();

    public CartEntity(String userId) {
        this.userId = userId;
        this.isActiveStatus = true;
    }

}
