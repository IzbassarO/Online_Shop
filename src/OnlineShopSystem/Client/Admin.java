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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Admin:\t"+ getUsername() + "\nID:\t" + getId() + "\nUsername:\t" + getUsername() + "\nPassword:\t" + getUsername();
    }
}
