package com.grocerytracker;

import java.util.Scanner;

public class Menu {
    private GroceryDAO dao;
    private GroceryService service;
    private Scanner sc;

    public Menu() {
        dao = new GroceryDAO();
        service = new GroceryService();
        sc = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== SMART GROCERY EXPIRY TRACKER ===");
            System.out.println("1. Add Grocery Item");
            System.out.println("2. View All Grocery Items");
            System.out.println("3. Show Items Expiring Soon");
            System.out.println("4. Delete an Item");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    dao.viewAllItems();
                    break;
                case 3:
                    checkExpiringItems();
                    break;
                case 4:
                    deleteItem();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void addItem() {
        System.out.print("Enter item name: ");
        String name = sc.nextLine();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter purchase date (YYYY-MM-DD): ");
        String pDate = sc.nextLine();
        System.out.print("Enter expiry date (YYYY-MM-DD): ");
        String eDate = sc.nextLine();

        GroceryItem item = new GroceryItem();
        item.setItemName(name);
        item.setQuantity(qty);
        item.setPurchaseDate(java.sql.Date.valueOf(pDate));
        item.setExpiryDate(java.sql.Date.valueOf(eDate));

        dao.addItem(item);
    }

    private void checkExpiringItems() {
        System.out.print("Enter days to check (e.g., 3): ");
        int days = sc.nextInt();
        sc.nextLine();
        service.showExpiringItems(days);
    }

    private void deleteItem() {
        System.out.print("Enter Item ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        dao.deleteItem(id);
    }
}
