package OnlineShopSystem.Category;

import OnlineShopSystem.Category.CategoryMethods.Product;
import OnlineShopSystem.Category.CategoryMethods.ProductMethod;
import OnlineShopSystem.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Laptop extends Product implements ProductMethod {
    private String category;

    public Laptop() {}

    public Laptop(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public void showProducts() {
        try {
            // Get database connection
            Connection conn = DatabaseConnection.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM laptops");
            System.out.println("ID\tName\tPrice\tCategory");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Laptop laptop = new Laptop(id, name, price);
                System.out.println(laptop.getId() + "\t" + laptop.getName() + "\t" + laptop.getPrice());
            }
        } catch (SQLException e) {
            System.out.println("Error displaying phones: " + e.getMessage());
        }
    }
}
