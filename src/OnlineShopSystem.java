import java.sql.*;
import java.util.Scanner;

public class OnlineShopSystem {
    private static Connection conn;
    static ProductInitialSample sample = new ProductInitialSample();
    public static void main(String[] args) throws SQLException {
        sample.addInitialData();
        Scanner sc = new Scanner(System.in);
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    conn.close();
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void register() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO clients (username, password, balance) VALUES (?, ?, 100)");
        stmt.setString(1, name);
        stmt.setString(2, password);
        stmt.executeUpdate();
        System.out.println("User registered successfully.");
    }

    public static void login() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        PreparedStatement stmt = conn.prepareStatement("SELECT id, balance FROM clients WHERE username = ? AND password = ?");
        stmt.setString(1, name);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            System.out.println("Invalid user data.");
            return;
        }

        int id = rs.getInt("id");
        int balance = rs.getInt("balance");
        System.out.println("Login successful.");

        while (true) {
            System.out.println("1. Buy product");
            System.out.println("2. Add balance");
            System.out.println("3. Logout");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    buyProduct(id, balance);
                    break;
                case 2:
                    addBalance(id);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    public static void buyProduct(int userId, int balance) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Laptops");
        System.out.println("2. Phones");
        System.out.println("3. TVs");
        System.out.println("4. Headphones");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        String tableName = "";
        switch (choice) {
            case 1:
                tableName = "laptops";
                break;
            case 2:
                tableName = "phones";
                break;
            case 3:
                tableName = "tvs";
                break;
            case 4:
                tableName = "headphones";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        PreparedStatement stmt = conn.prepareStatement("SELECT id, price, name FROM " + tableName);
        ResultSet rs = stmt.executeQuery();

        System.out.println("Products:");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + ". " + rs.getString("name") + " " + rs.getInt("price"));
        }

        System.out.print("Enter product id: ");
        int productId = sc.nextInt();
        PreparedStatement stmt2 = conn.prepareStatement("SELECT price FROM " + tableName + " WHERE id = ?");
        stmt2.setInt(1, productId);
        ResultSet rs2 = stmt2.executeQuery();

        int productPrice = 0;
        if (rs2.next()) {
            productPrice = rs2.getInt("price");
        }

        if (productPrice <= balance) {
            //Not properly understand
            PreparedStatement stmt3 = conn.prepareStatement("DELETE FROM " + tableName + " WHERE id = ?");
            stmt3.setInt(1, productId);
            stmt3.executeUpdate();

            PreparedStatement stmt4 = conn.prepareStatement("UPDATE clients SET balance = ? WHERE id = ?");
            stmt4.setInt(1, balance - productPrice);
            stmt4.setInt(2, userId);
            stmt4.executeUpdate();

            System.out.println("Product bought successfully. Now you have " + (balance - productPrice) + " balance");
        } else {
            System.out.println("Not enough balance to buy this product");
        }
    }

    public static void addBalance(int userId) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter amount to add: ");
        int amount = sc.nextInt();
        sc.nextLine();

        PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM clients WHERE id = ?");
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        int currentBalance = 0;
        if (rs.next()) {
            currentBalance = rs.getInt("balance");
        }

        PreparedStatement stmt2 = conn.prepareStatement("UPDATE clients SET balance = ? WHERE id = ?");
        stmt2.setInt(1, currentBalance + amount);
        stmt2.setInt(2, userId);
        stmt2.executeUpdate();

        PreparedStatement stmt3 = conn.prepareStatement("SELECT username FROM clients WHERE id = ? ");
        stmt3.setInt(1, userId);
        ResultSet rs3 = stmt3.executeQuery();

        String userName = "";
        if (rs3.next()) {
            userName = rs3.getString("username");
        }

        System.out.println("Balance added successfully. Now " + userName + " has " + (currentBalance + amount));
    }
}