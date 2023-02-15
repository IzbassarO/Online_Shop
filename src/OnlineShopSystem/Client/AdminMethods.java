package OnlineShopSystem.Client;

import OnlineShopSystem.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;



public class AdminMethods {
    static Connection conn = DatabaseConnection.getConnection();
    static Scanner scanner = new Scanner(System.in);
    static PreparedStatement ps = null;
    public static void AddProduct(Admin admin) {
        try {
            System.out.println("Enter the name of the product:");
            String name = scanner.next();

            System.out.println("Enter the category of the product:");
            String category = scanner.next();

            System.out.println("Enter the price of the product:");
            int price = scanner.nextInt();

            // Prepare statement to insert the new product into the database
            ps = conn.prepareStatement("INSERT INTO products(name, category, price) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setInt(3, price);

            // Execute the statement to insert the new product
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Product added successfully.");
            } else {
                System.out.println("Failed to add product.");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while trying to add a product: " + e.getMessage());
        }
    }


    public static void ShowAllUsers(Admin admin) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM clients");
        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("No users found.");
            return;
        }

        System.out.println("All users:");
        System.out.println("ID\tUsername\tPassword\tBalance");

        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            double balance = rs.getDouble("balance");
            System.out.println(id + "\t" + username + "\t" + password + "\t" + balance);
        }
    }


    public static void RemoveUser(Admin admin) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the user to be removed:");
        int userId = scanner.nextInt();
        ps = conn.prepareStatement("DELETE FROM clients WHERE id = ?");
        ps.setInt(1, userId);
        int rows = ps.executeUpdate();
        if (rows > 0) {
            System.out.println("User with ID " + userId + " removed successfully.");
        } else {
            System.out.println("Failed to remove user with ID " + userId + ".");
        }
    }


    public static void ShowAdminDetails(Admin admin) {
        admin.toString();
    }
    public static void searchByCategory() throws SQLException {
        System.out.println("Write the number of category:");
        System.out.println("1. Headphone");
        System.out.println("2. Laptop");
        System.out.println("3. TV");
        System.out.println("4. Phone");

        int category = scanner.nextInt();
        String categoryStr;

        switch (category) {
            case 1:
                categoryStr = "Headphone";
                break;
            case 2:
                categoryStr = "Laptop";
                break;
            case 3:
                categoryStr = "TV";
                break;
            case 4:
                categoryStr = "Phone";
                break;
            default:
                System.out.println("There is no such category!");
                return;
        }

        String query = "SELECT * FROM products WHERE category = ?";
        ps = conn.prepareStatement(query);
        ps.setString(1, categoryStr);
        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("No products found in the " + categoryStr + " category.");
            return;
        }

        System.out.println("Products in the " + categoryStr + " category:");
        System.out.println("ID\tName\tPrice");

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            System.out.println(id + "\t" + name + "\t" + price);
        }
    }



}
