package OnlineShopSystem.Category;

import OnlineShopSystem.Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Laptop {
    private int id;
    private String name;
    private double price;

    public Laptop(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public static void showLaptops() {
        try {
            // Get database connection
            Connection conn = DatabaseConnection.getConnection();

            PreparedStatement showLaptopsStmt = conn.prepareStatement("SELECT * FROM laptops");
            ResultSet laptopsRs = showLaptopsStmt.executeQuery();
            while (laptopsRs.next()) {
                int id = laptopsRs.getInt("id");
                String name = laptopsRs.getString("name");
                double price = laptopsRs.getDouble("price");
                System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving laptops from the database: " + e.getMessage());
        }
    }
}
