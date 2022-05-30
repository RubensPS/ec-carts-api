package com.letscode.eccartsapi.controller;

import com.letscode.eccartsapi.domain.AddProductRequest;
import com.letscode.eccartsapi.domain.CartRequest;
import com.letscode.eccartsapi.domain.CartResponse;
import com.letscode.eccartsapi.gateway.UserGateway;
import com.letscode.eccartsapi.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserGateway userGateway;

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addNewCart(@RequestBody CartRequest request) {
        return cartService.addCart(request);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<CartResponse> getCartById(@PathVariable String id) {
        CartResponse response= cartService.getCartById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cart/user/{userId}")
    public ResponseEntity<CartResponse> getActiveCart(@PathVariable String userId) {
        List<CartResponse> response = cartService.getActiveCart(userId);
        if(response.size() == 1) {
            return ResponseEntity.ok(response.get(0));
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @DeleteMapping("/cart/remove/{userId}")
    public ResponseEntity<String> deleteActiveCart(@PathVariable String userId) {
        return cartService.deleteCart(userId);
    }

    @PostMapping("/add/product")
    public ResponseEntity<CartResponse> addNewProduct(@RequestBody AddProductRequest request) {
        ResponseEntity<String> userResponse = userGateway.getUser(request.getUserId());
        if(!userResponse.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        List<CartResponse> checkCart = cartService.getActiveCart(request.getUserId().toString());
        if (checkCart.size() == 0) {
            cartService.addCart(new CartRequest(request.getUserId().toString()));
        }
        CartResponse response = cartService.addProduct(request);
        return ResponseEntity.ok(response);
    }

}
