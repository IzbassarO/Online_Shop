package OnlineShopSystem.Entities.Admin;

public class Admin{
    private int id;
    private String username;
    private String password;
    public Admin(int id, String username, String password) {
        setId(id);
        setUsername(username);
        setPassword(password);
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
