package OnlineShopSystem.Repository;

import OnlineShopSystem.Client.Clients;
import OnlineShopSystem.Client.UserMethods;
import OnlineShopSystem.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static Connection conn;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        // Connect to the database
        DatabaseConnection.getConnection();

        // Show main menu
        while (true) {
            System.out.println("Online Shop System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Show available laptops");
            System.out.println("4. Show available TVs");
            System.out.println("5. Show available headphones");
            System.out.println("6. Show available phones");
            System.out.println("7. Exit");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    UserMethods.registerUser();
                    break;
                case 2:
                    Clients client = UserMethods.loginUser();
                    if (client != null) {
                        showClientMenu(client);
                    }
                    break;
                case 3:
                    Laptop.showLaptops();
                    break;
                case 4:
                    TV.showTVs();
                    break;
                case 5:
                    Headphone.showHeadphones();
                    break;
                case 6:
                    Phone.showPhones();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }
        }
    }
}
