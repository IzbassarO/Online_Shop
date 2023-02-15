package OnlineShopSystem.Category;

import OnlineShopSystem.Database.DatabaseConnection;
import OnlineShopSystem.Repository.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Phone extends Product implements ProductMethod{
    public Phone() {}

    public Phone(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public void showProducts() {
        try {
            // Get database connection
            Connection conn = DatabaseConnection.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM phones");
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
