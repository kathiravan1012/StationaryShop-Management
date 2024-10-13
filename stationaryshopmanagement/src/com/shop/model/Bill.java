package com.shop.model;
import java.util.ArrayList;
import java.util.List;

public class Bill {
	private List<Product> products;
    private double totalAmount;

    public Bill() {
        this.products = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addProduct(Product product, int quantity) {
        products.add(product);
        totalAmount += product.getPrice() * quantity;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

}
