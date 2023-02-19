package OnlineShopSystem.Entities.Clients;

public class Clients {
    private int id;
    private String username;
    private String password;
    private double balance;

    public Clients(int id, String username, String password, double balance) {
        setId(id);
        setUsername(username);
        setPassword(password);
        setBalance(balance);
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
