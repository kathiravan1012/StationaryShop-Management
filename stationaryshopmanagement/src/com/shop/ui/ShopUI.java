package com.shop.ui;
import com.shop.model.Bill;
import com.shop.model.Product;
import com.shop.service.InventoryService;
import java.util.Scanner;

public class ShopUI {
	 private InventoryService inventoryService;
	    private Scanner scanner;

	    public ShopUI() {
	        inventoryService = new InventoryService();
	        scanner = new Scanner(System.in);
	    }

	    public void start() {
	        while (true) {
	            System.out.println("1. Add Product");
	            System.out.println("2. Create Bill");
	            System.out.println("3. View Inventory");
	            System.out.println("4. Exit");
	            System.out.print("Choose an option: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    addProduct();
	                    break;
	                case 2:
	                    createBill();
	                    break;
	                case 3:
	                    viewInventory();
	                    break;
	                case 4:
	                    System.exit(0);
	            }
	        }
	    }

	    private void addProduct() {
	        System.out.print("Enter product ID: ");
	        String id = scanner.next();
	        System.out.print("Enter product name: ");
	        String name = scanner.next();
	        System.out.print("Enter product price: ");
	        double price = scanner.nextDouble();
	        System.out.print("Enter product quantity: ");
	        int quantity = scanner.nextInt();

	        Product product = new Product(id, name, price, quantity);
	        inventoryService.addProduct(product);
	        System.out.println("Product added successfully!");
	    }

	    private void createBill() {
	        Bill bill = new Bill();

	        while (true) {
	            System.out.print("Enter product ID to add to bill (or 'done' to finish): ");
	            String productId = scanner.next();

	            if (productId.equals("done")) {
	                break;
	            }

	            Product product = inventoryService.getProduct(productId);
	            if (product != null) {
	                System.out.print("Enter quantity: ");
	                int quantity = scanner.nextInt();
	                bill.addProduct(product, quantity);
	            } else {
	                System.out.println("Product not found!");
	            }
	        }

	        System.out.println("Bill created. Total amount: " + bill.getTotalAmount());
	    }

	    private void viewInventory() {
	        System.out.println("Inventory:");
	        for (Product product : inventoryService.getInventory().values()) {
	            System.out.println("ID: " + product.getId() + ", Name: " + product.getName() +
	                    ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity());
	        }
	    }

	    public static void main(String[] args) {
	        new ShopUI().start();
	    }

}
