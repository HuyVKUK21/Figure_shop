package com.example.figureshop.service.model;

import com.example.figureshop.entity.Product;

public class ProductWithQuantity {
    private final Product product;
    private final int quantity;

    public ProductWithQuantity(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
