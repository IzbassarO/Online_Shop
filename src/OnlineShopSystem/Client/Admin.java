package OnlineShopSystem.Client;

import OnlineShopSystem.Repository.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin{
    private int id;
    private String username;
    private String password;
    public Admin(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public void openAdminAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter admin username: ");
        String adminName = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String adminPass = scanner.nextLine();

        try {
            PreparedStatement stmt = Main.conn.prepareStatement("Select * FROM admin WHERE password");
            try {
                stmt.setString(1, adminPass);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error saving user to database: " + e.getMessage());
        }
    }
}
