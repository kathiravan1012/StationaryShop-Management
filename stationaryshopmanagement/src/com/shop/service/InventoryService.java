package com.shop.service;
import com.shop.model.Product;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class InventoryService {
	 private static final String DB_URL = "\"C:\\Users\\kathi\\OneDrive\\Documents\\stationary_shop.sql\"";
	    private static final String DB_USER = "root"; // replace with your DB username
	    private static final String DB_PASSWORD = "root"; // replace with your DB password

	    private Connection connection;

	    public InventoryService() {
	        try {
	            connection = DriverManager.getConnection("C:\\Users\\kathi\\OneDrive\\Documents\\stationary_shop.sql", root, root);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void addProduct(Product product) {
	        String query = "INSERT INTO products (id, name, price, quantity) VALUES ('P001', 'Notebook', 2.50, 100),('P002', 'Pen', 1.20, 200),('P003', 'Pencil', 0.80, 300)";

	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, product.getId());
	            stmt.setString(2, product.getName());
	            stmt.setDouble(3, product.getPrice());
	            stmt.setInt(4, product.getQuantity());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public Product getProduct(String productId) {
	        String query = "SELECT * FROM products WHERE id = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, productId);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                return new Product(
	                        rs.getString("id"),
	                        rs.getString("name"),
	                        rs.getDouble("price"),
	                        rs.getInt("quantity")
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public void updateProductQuantity(String productId, int quantity) {
	        String query = "UPDATE products SET quantity = 1 WHERE id = 2";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setInt(1, quantity);
	            stmt.setString(2, productId);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public Map<String, Product> getInventory() {
	        Map<String, Product> inventory = new HashMap<>();
	        String query = "SELECT * FROM products";
	        try (Statement stmt = connection.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                Product product = new Product(
	                        rs.getString("id"),
	                        rs.getString("name"),
	                        rs.getDouble("price"),
	                        rs.getInt("quantity")
	                );
	                inventory.put(product.getId(), product);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return inventory;
	    }
	 
	    }


