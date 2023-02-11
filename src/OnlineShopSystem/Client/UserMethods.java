package OnlineShopSystem.Client;

import OnlineShopSystem.Client.User;
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

}
