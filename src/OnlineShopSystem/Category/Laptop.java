package OnlineShopSystem.Category;

import OnlineShopSystem.Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Laptop extends Product implements ProductMethod {
    public Laptop() {}

    public Laptop(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public void showProducts() {
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
