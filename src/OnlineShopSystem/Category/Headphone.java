package OnlineShopSystem.Category;

import OnlineShopSystem.Database.DatabaseConnection;
import OnlineShopSystem.Repository.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Headphone {
    private int id;
    private String name;
    private double price;

    public Headphone() {}

    public Headphone(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static void showHeadphones() {
        try {
            // Get database connection
            Connection conn = DatabaseConnection.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM headphones");
            System.out.println("ID\tName\tPrice");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                System.out.println(id + "\t" + name + "\t" + price);
            }
        } catch (SQLException e) {
            System.out.println("Error displaying TVs: " + e.getMessage());
        }
    }
}
