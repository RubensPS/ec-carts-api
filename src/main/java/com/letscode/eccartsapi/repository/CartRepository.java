package com.letscode.eccartsapi.repository;

import com.letscode.eccartsapi.domain.CartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CartRepository extends MongoRepository<CartEntity, String> {

    @Query("{userId: ?0, isActiveStatus: ?1}")
    List<CartEntity> getActiveCart(String userId, Boolean isActiveStatus);

}
