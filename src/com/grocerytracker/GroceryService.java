package com.grocerytracker;


import java.sql.*;

public class GroceryService {
    public void showExpiringItems(int days) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM grocery_items WHERE expiry_date <= DATE_ADD(CURDATE(), INTERVAL ? DAY)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, days);
            ResultSet rs = ps.executeQuery();
            System.out.println("Items expiring in next " + days + " days:");
            while (rs.next()) {
                System.out.println(rs.getString("item_name") + " - Expiry: " + rs.getDate("expiry_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
