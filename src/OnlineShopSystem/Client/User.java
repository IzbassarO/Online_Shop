package OnlineShopSystem.Client;

import OnlineShopSystem.Category.Headphone;
import OnlineShopSystem.Category.Laptop;
import OnlineShopSystem.Category.Phone;
import OnlineShopSystem.Category.TV;
import OnlineShopSystem.Repository.Main;
import java.sql.PreparedStatement;
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
}
