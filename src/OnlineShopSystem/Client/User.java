package OnlineShopSystem.Client;

import OnlineShopSystem.Repository.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String username;
    private String password;
    private double balance;

    public User(int id, String username, String password, double balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void saveToDB() {
        try {
            PreparedStatement stmt = Main.conn.prepareStatement("INSERT INTO user (username, password) values (?, ?)");
            stmt.setString(1, this.username);
            stmt.setString(2, this.password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving user to database: " + e.getMessage());
        }
    }
}
