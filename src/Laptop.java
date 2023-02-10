import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Laptop extends Product {
    private static Connection conn;
    private int screenSize;
    private int storage;

    public Laptop(int id, String name, int price, int screenSize, int storage) {
        super(id, name, price);
        this.screenSize = screenSize;
        this.storage = storage;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public int getStorage() {
        return storage;
    }
    private static void addLaptop(Laptop laptop) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO laptops (id, name, price, screen_size, storage) VALUES (?, ?, ?, ?, ?)");
        stmt.setInt(1, laptop.getId());
        stmt.setString(2, laptop.getName());
        stmt.setInt(3, laptop.getPrice());
        stmt.setInt(4, laptop.getScreenSize());
        stmt.setInt(5, laptop.getStorage());
        stmt.executeUpdate();
    }

}
