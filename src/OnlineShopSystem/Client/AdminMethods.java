package OnlineShopSystem.Client;

import OnlineShopSystem.Category.*;
import OnlineShopSystem.Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminMethods {
    static Connection conn = DatabaseConnection.getConnection();
    static Scanner scanner = new Scanner(System.in);
    static PreparedStatement ps = null;
    public static void AddProduct(Admin admin) {

    }

    public static void ShowAllUsers(Admin admin) {

    }

    public static void RemoveUser(Admin admin) throws SQLException {
        int id = admin.getId();
        ps = conn.prepareStatement("DELETE FROM clients where id = ?");
        ps.setInt(1, id);
        ps.execute();
    }

    public static void ShowAdminDetails(Admin admin) {
        admin.toString();
    }

    public static void searchByCategory() throws SQLException {
        System.out.println("Write the number of category:");
        System.out.println("1. Headphone");
        System.out.println("1. Laptop");
        System.out.println("1. TV");
        System.out.println("1. Phone");

        int x = scanner.nextInt();
        switch (x) {
            case 1 -> user.showProducts();
            case 2 ->
            case 3 ->
            case 4 ->
            case 5 ->
            default -> {
                System.out.println("There is no such category!");
            }
        }
    }
}
