package OnlineShopSystem.Repository;

import OnlineShopSystem.Category.Headphone;
import OnlineShopSystem.Category.Laptop;
import OnlineShopSystem.Category.Phone;
import OnlineShopSystem.Category.TV;
import OnlineShopSystem.Entities.Clients.ClientMethods;
import OnlineShopSystem.Entities.Clients.Clients;
import OnlineShopSystem.Entities.User.UserMethods;
import OnlineShopSystem.Entities.Admin.Admin;
import OnlineShopSystem.Entities.Admin.AdminMethods;
import java.sql.SQLException;
import java.util.Scanner;

class MainApplication {
    private static final Scanner scanner = new Scanner(System.in);

    private static final Laptop laptop = new Laptop();
    private static final Phone phone = new Phone();
    private static final TV tv = new TV();
    private static final Headphone headphone = new Headphone();

    public static void run() throws SQLException {
        // Show main menu
        while (true) {
            System.out.println("Online Shop System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Show available laptops");
            System.out.println("4. Show available TVs");
            System.out.println("5. Show available headphones");
            System.out.println("6. Show available phones");
            System.out.println("7. Login as admin");
            System.out.println("8. Exit");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> UserMethods.registerUser();
                case 2 -> {
                    Clients client = UserMethods.loginUser();
                    if (client != null) {
                        showClientMenu(client);
                    }
                }
                case 3 -> laptop.showProducts();
                case 4 -> tv.showProducts();
                case 5 -> headphone.showProducts();
                case 6 -> phone.showProducts();
                case 7 -> {
                    Admin admin = UserMethods.loginAdmin();
                    if (admin != null) {
                        showAdminMenu(admin);
                    }
                }
                case 8 -> System.exit(0);
                default -> System.out.println("Invalid option, try again.");
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
                case 1 -> ClientMethods.buyProduct(client);
                case 2 -> ClientMethods.addBalance(client);
                case 3 -> ClientMethods.showStatus(client);
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }

    private static void showAdminMenu(Admin admin) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. Add product");
            System.out.println("2. Show all users");
            System.out.println("3. Remove a user");
            System.out.println("4. Show admin details");
            System.out.println("5. Search by category");
            System.out.println("6. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character left by nextInt()

            switch (choice) {
                case 1 -> AdminMethods.AddProduct(admin);
                case 2 -> AdminMethods.ShowAllUsers(admin);
                case 3 -> AdminMethods.RemoveUser(admin);
                case 4 -> AdminMethods.ShowDetails(admin);
                case 5 -> AdminMethods.SearchByCategory();
                case 6 -> {return;}
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }

}

