package OnlineShopSystem.Client;

import OnlineShopSystem.Category.Headphone;
import OnlineShopSystem.Category.Laptop;
import OnlineShopSystem.Category.Phone;
import OnlineShopSystem.Category.TV;
import OnlineShopSystem.Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ClientMethods {
    public static void buyProduct(OnlineShopSystem.Client.Clients client) throws SQLException {
        // Get database connection
        Connection conn = DatabaseConnection.getConnection();

        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                There are several categories choose one:
                1. Laptop
                2. TV
                3. Headphone
                4. Phone""");
        int choice = scanner.nextInt();
        String category = "";
        switch (choice) {
            case 1:
                category = "laptops";
                Laptop.showLaptops();
                break;
            case 2:
                category = "tvs";
                TV.showTVs();
                break;
            case 3:
                category = "headphones";
                Headphone.showHeadphones();
                break;
            case 4:
                category = "phones";
                Phone.showPhones();
                break;
        }
        System.out.println("Enter product id: ");
        int productId = scanner.nextInt();

        PreparedStatement stmt = conn.prepareStatement("SELECT price FROM " + category + " WHERE id = ?");
        try {
            stmt.setInt(1, productId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet rs = stmt.executeQuery();

        double productPrice = 0;
        if (rs.next()) {
            productPrice = rs.getInt("price");
        }

        // Check if the client has enough balance to buy the product
        if (client.getBalance() < productPrice) {
            System.out.println("Insufficient balance, please add balance.");
            return;
        }

        // Update the client's balance
        client.setBalance(client.getBalance() - productPrice);

        // Update the client's information in the database
        try {
            PreparedStatement stmt3 = conn.prepareStatement("DELETE FROM " + category + " WHERE id = ?");
            stmt3.setInt(1, productId);
            stmt3.executeUpdate();

            PreparedStatement stmt4 = conn.prepareStatement("UPDATE clients SET balance = ? WHERE id = ?");
            stmt4.setDouble(1, client.getBalance() - productPrice);
            stmt4.setInt(2, client.getId());
            System.out.println("Product bought successfully. Now you have " + (client.getBalance() - productPrice) + " balance");

            stmt4.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating client's information in the database: " + e.getMessage());
        }
    }

    public static void addBalance(OnlineShopSystem.Client.Clients client) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter amount to add: ");
        double amount = scanner.nextDouble();

        // Get database connection
        Connection conn = DatabaseConnection.getConnection();

        // Update the client's balance
        client.setBalance(client.getBalance() + amount);

        // Get client's current balance
        PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM clients WHERE id = ?");
        stmt.setInt(1, client.getId());
        ResultSet rs = stmt.executeQuery();

        double currentBalance = 0;
        if (rs.next()) {
            currentBalance = rs.getDouble("balance");
        }


        PreparedStatement stmt3 = conn.prepareStatement("SELECT username FROM clients WHERE id = ? ");
        stmt3.setInt(1, client.getId());
        ResultSet rs3 = stmt3.executeQuery();

        String name = "";
        if (rs3.next()) {
            name = rs3.getString("username");
        }

        // Update the client's information in the database
        try {
            PreparedStatement stmt2 = conn.prepareStatement("UPDATE clients SET balance = ? WHERE id = ?");
            stmt2.setDouble(1, currentBalance + amount);
            stmt2.setInt(2, client.getId());
            stmt2.executeUpdate();
            stmt2.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating client's information in the database: " + e.getMessage());
        }

        System.out.println("Balance added successfully. Now " + name + " has " + (currentBalance + amount));
    }
}