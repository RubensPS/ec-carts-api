package com.letscode.eccartsapi.service;

import com.letscode.eccartsapi.domain.AddProductRequest;
import com.letscode.eccartsapi.domain.CartEntity;
import com.letscode.eccartsapi.domain.CartRequest;
import com.letscode.eccartsapi.domain.CartResponse;
import com.letscode.eccartsapi.gateway.ProductPriceGateway;
import com.letscode.eccartsapi.repository.CartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductPriceGateway productPriceGateway;

    public CartService(CartRepository cartRepository, ProductPriceGateway productPriceGateway) {
        this.cartRepository = cartRepository;
        this.productPriceGateway = productPriceGateway;
    }

    public ResponseEntity<CartResponse> addCart(CartRequest request) {
        List<CartEntity> entities = this.cartRepository.getActiveCart(request.getUserId(), true);
        if (!entities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        CartEntity entity = request.toEntity();
        CartResponse response = new CartResponse(this.cartRepository.save(entity));
        return ResponseEntity.ok(response);
    }

    public CartResponse getCartById(String id) {
        Optional<CartEntity> entity = this.cartRepository.findById(id);
        return new CartResponse(entity.get());
    }

    public List<CartResponse> getActiveCart(String userId) {
        List<CartEntity> entity = this.cartRepository.getActiveCart(userId, true);
        return entity.stream().map(e -> new CartResponse(e)).toList();
    }

    public ResponseEntity<String> changeStatus(String cartId) {
        Optional<CartEntity> entity = cartRepository.findById(cartId);
        if (entity.isEmpty()) {
            return ResponseEntity.unprocessableEntity().body("Cart not found. Check imput data.");
        }
        if (!entity.get().getIsActiveStatus()) {
            return ResponseEntity.unprocessableEntity().body("Cart is already inactive.");
        }
        entity.get().setIsActiveStatus(false);
        cartRepository.save(entity.get());
        return ResponseEntity.ok().body("Cart status updated to inactive.");
    }

    public ResponseEntity<String> deleteCart(String userId) {
        List<CartEntity> entities = this.cartRepository.getActiveCart(userId, true);
        if (entities.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cartRepository.deleteById(entities.get(0).getId());
        return ResponseEntity.ok("Cart DELETED successfully.");
    }

    public CartResponse addProduct(AddProductRequest request) {
        CartEntity entity = this.cartRepository.getActiveCart(request.getUserId(), true).get(0);
        entity.getProducts().merge(request.getProductId(), request.getQuantity(), (oldQuantity, newQuantity) -> (oldQuantity + newQuantity));
        BigDecimal totalPrice = entity.getProducts().entrySet().stream()
                .map(p -> (productPriceGateway.getPrice(p.getKey()).getBody()
                        .multiply(BigDecimal.valueOf(p.getValue()))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        entity.setTotalPrice(totalPrice);
        CartResponse response = new CartResponse(this.cartRepository.save(entity));
        return response;
    }

}
