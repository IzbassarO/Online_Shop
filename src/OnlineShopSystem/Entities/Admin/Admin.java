package OnlineShopSystem.Entities.Admin;

import OnlineShopSystem.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin{
    private int id;
    private String username;
    private String password;
    public Admin(int id, String username, String password) {
        setId(id);
        setUsername(username);
        setPassword(password);
    }

    public boolean isAdmin() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM admin WHERE username = ? AND password = ?");
            ps.setString(1, this.username);
            ps.setString(2, this.password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("An error occurred while trying to check the admin status: " + e.getMessage());
            return false;
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
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
}
