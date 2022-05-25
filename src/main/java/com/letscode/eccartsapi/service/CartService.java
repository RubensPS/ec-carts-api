package com.letscode.eccartsapi.service;

import com.letscode.eccartsapi.domain.AddProductRequest;
import com.letscode.eccartsapi.domain.CartEntity;
import com.letscode.eccartsapi.domain.CartRequest;
import com.letscode.eccartsapi.domain.CartResponse;
import com.letscode.eccartsapi.repository.CartRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartResponse addCart(CartRequest request) {
        CartEntity entity = request.toEntity();
        CartResponse response = new CartResponse(this.cartRepository.save(entity));
        return response;
    }

    public CartResponse getCartById(String id) {
        Optional<CartEntity> entity = this.cartRepository.findById(id);
        return new CartResponse(entity.get());
    }

    public List<CartResponse> getActiveCart(String id) {
        List<CartEntity> entity = this.cartRepository.getActiveCart(id, true);
        return entity.stream().map(e -> new CartResponse(e)).toList();
    }

    public CartResponse addProduct(AddProductRequest request) {
        CartEntity entity = this.cartRepository.getActiveCart(request.getUserId().toString(), true).get(0);
        entity.getProducts().merge(request.getProductId(), request.getQuantity(), (oldQuantity, newQuantity) -> (oldQuantity + newQuantity));
        CartResponse response = new CartResponse(this.cartRepository.save(entity));
        return response;
    }

}
