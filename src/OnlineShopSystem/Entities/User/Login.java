package OnlineShopSystem.Entities.User;

import OnlineShopSystem.Database.DatabaseConnection;
import OnlineShopSystem.Entities.Clients.Clients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    public static Clients loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        // Get database connection
        Connection conn = DatabaseConnection.getConnection();

        // Check if the username and password are correct
        try {
            PreparedStatement checkLoginStmt = conn.prepareStatement("SELECT * FROM clients WHERE username = ? AND password = ?");
            checkLoginStmt.setString(1, username);
            checkLoginStmt.setString(2, password);
            ResultSet resultSet = checkLoginStmt.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Incorrect username or password.");
                return null;
            }
            int id = resultSet.getInt("id");
            double balance = resultSet.getDouble("balance");

            return new Clients(id, username, password, balance);
        } catch (SQLException e) {
            System.out.println("Error checking login in the database: " + e.getMessage());
            return null;
        }
    }
}
