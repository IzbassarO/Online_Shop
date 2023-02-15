package OnlineShopSystem.Repository;

import OnlineShopSystem.Client.*;
import OnlineShopSystem.Database.DatabaseConnection;
import OnlineShopSystem.Category.Laptop;
import OnlineShopSystem.Category.Headphone;
import OnlineShopSystem.Category.Phone;
import OnlineShopSystem.Category.TV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static Connection conn;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        // Connect to the database
        DatabaseConnection.getConnection();

        // Create object of categories
        Laptop laptop = new Laptop();
        Phone phone = new Phone();
        TV tv = new TV();
        Headphone headphone = new Headphone();

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
                    laptop.showProducts();
                    break;
                case 4:
                    tv.showProducts();
                    break;
                case 5:
                    headphone.showProducts();
                    break;
                case 6:
                    phone.showProducts();
                    break;
                case 7:
                    Admin admin = UserMethods.loginAdmin();
                    if(admin != null) {
                        showAdminMenu(admin);
                    }
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }
        }
    }

    private static void showClientMenu(Clients client) throws SQLException {
        while (true) {
            System.out.println("Client Menu");
            System.out.println("1. Buy product");
            System.out.println("2. Add balance");
            System.out.println("3. Show account status");
            System.out.println("4. Logout");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    ClientMethods.buyProduct(client);
                    break;
                case 2:
                    ClientMethods.addBalance(client);
                    break;
                case 3:
                    ClientMethods.showStatus(client);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }
        }
    }
    private static void showAdminMenu(Admin admin) throws SQLException {
        while (true) {
            System.out.println("Client Menu");
            System.out.println("1. Add product");
            System.out.println("2. Show all users");
            System.out.println("3. Remove a user");
            System.out.println("4. Logout");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    AdminMethods.AddProduct(admin);
                    break;
                case 2:
                    AdminMethods.ShowAllUsers(admin);
                    break;
                case 3:
                    AdminMethods.RemoveUser(admin);
                    break;
                case 4:
                    AdminMethods.ShowAdminDetails(admin);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }
        }
    }
}
