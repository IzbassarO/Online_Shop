package OnlineShopSystem.Entities.User;
import OnlineShopSystem.Database.DatabaseConnection;
import OnlineShopSystem.Entities.Admin.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginAdmin {
    public static Admin loginAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        // Get database connection
        Connection conn = DatabaseConnection.getConnection();

        // Check if the username and password are correct
        try {
            PreparedStatement checkLoginStmt = conn.prepareStatement("SELECT * FROM admin WHERE username = ? AND password = ?");
            checkLoginStmt.setString(1, username);
            checkLoginStmt.setString(2, password);
            ResultSet resultSet = checkLoginStmt.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Incorrect username or password.");
                return null;
            }
            int id = resultSet.getInt("id");

            return new Admin(id, username, password);
        } catch (SQLException e) {
            System.out.println("Error checking login in the database: " + e.getMessage());
            return null;
        }
    }
}
