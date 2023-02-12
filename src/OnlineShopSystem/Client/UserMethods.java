package OnlineShopSystem.Client;
import OnlineShopSystem.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserMethods {

    public static User registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        // Get database connection
        Connection conn = DatabaseConnection.getConnection();

        // Check if the username already exists
        try {
            PreparedStatement checkUsernameStmt = conn.prepareStatement("SELECT * FROM clients WHERE username = ?");
            checkUsernameStmt.setString(1, username);
            ResultSet resultSet = checkUsernameStmt.executeQuery();
            if (resultSet.next()) {
                System.out.println("Username already exists, please choose another username.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error checking username in the database: " + e.getMessage());
            return null;
        }

        // Insert the new user into the database
        try {
            PreparedStatement insertUserStmt = conn.prepareStatement("INSERT INTO clients (username, password) VALUES (?, ?)");
            insertUserStmt.setString(1, username);
            insertUserStmt.setString(2, password);
            insertUserStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting user into the database: " + e.getMessage());
            return null;
        }

        int id = 0;
        double balance = 0.0;

        // Get the id and balance of the new user
        try {
            PreparedStatement getUserStmt = conn.prepareStatement("SELECT * FROM clients WHERE username = ? AND password = ?");
            getUserStmt.setString(1, username);
            getUserStmt.setString(2, password);
            ResultSet resultSet = getUserStmt.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
                balance = resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            System.out.println("Error getting user from the database: " + e.getMessage());
            return null;
        }

        return new User(id, username, password, balance);
    }

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
