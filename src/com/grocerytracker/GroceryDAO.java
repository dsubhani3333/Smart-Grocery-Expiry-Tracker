package com.grocerytracker;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GroceryDAO {
    public void addItem(GroceryItem item) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO grocery_items (item_name, quantity, purchase_date, expiry_date) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, item.getItemName());
            ps.setInt(2, item.getQuantity());
            ps.setDate(3, item.getPurchaseDate());
            ps.setDate(4, item.getExpiryDate());
            ps.executeUpdate();
            System.out.println("Item added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 // View all grocery items
    public void viewAllItems() {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM grocery_items ORDER BY expiry_date ASC";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.println("\n--- Grocery Items ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("item_id") +
                                   ", Name: " + rs.getString("item_name") +
                                   ", Qty: " + rs.getInt("quantity") +
                                   ", Purchase: " + rs.getDate("purchase_date") +
                                   ", Expiry: " + rs.getDate("expiry_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete grocery item by ID
    public void deleteItem(int id) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "DELETE FROM grocery_items WHERE item_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Item deleted successfully!");
            } else {
                System.out.println("Item not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
