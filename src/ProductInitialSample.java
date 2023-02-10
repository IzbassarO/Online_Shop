import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class ProductInitialSample {
    private static final String URL = "jdbc:postgresql://localhost:5432/OnlineShop";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection conn;
//static
    public static void addInitialData() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        addLaptops();
        addPhones();
        addTVs();
        addHeadphones();

        conn.close();
    }

    private static void addLaptops() throws SQLException {
        String[] laptops = {"HP Pavilion", "Dell Inspiron", "Lenovo ThinkPad", "Acer Aspire", "MacBook Pro",
                "Asus Chromebook", "MSI Trident", "HP Spectre", "Dell XPS", "Lenovo Ideapad"};
        int[] prices = {999, 899, 1199, 799, 1999, 599, 1499, 1999, 999, 799};

        for (int i = 0; i < laptops.length; i++) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO laptops(name, price) VALUES (?,?)");
            stmt.setString(1, laptops[i]);
            stmt.setInt(2, prices[i]);
            stmt.executeUpdate();
        }
    }

    private static void addPhones() throws SQLException {
        String[] phones = {"iPhone 11", "Samsung Galaxy S21", "Google Pixel 4a", "OnePlus 9", "Motorola Moto G Power",
                "Samsung Galaxy A52", "Apple iPhone SE", "Google Pixel 5", "OnePlus Nord", "Motorola Edge+"};
        int[] prices = {699, 799, 349, 599, 249, 399, 399, 699, 499, 999};

        for (int i = 0; i < phones.length; i++) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO phones(name, price) VALUES (?,?)");
            stmt.setString(1, phones[i]);
            stmt.setInt(2, prices[i]);
            stmt.executeUpdate();
        }
    }

    private static void addTVs() throws SQLException {
        String[] tvs = {"Samsung QN55Q60T", "LG OLED55CXPUA", "Vizio M-Series Quantum", "TCL 6-Series", "Sony X900H",
                "Insignia Fire TV", "Samsung TU8000", "LG UN7300", "Hisense H9G", "TCL 5-Series"};
        int[] prices = {799, 1199, 699, 479, 999, 329, 499, 499, 599, 379};

        for (int i = 0; i < tvs.length; i++) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tvs(name, price) VALUES (?,?)");
            stmt.setString(1, tvs[i]);
            stmt.setInt(2, prices[i]);
            stmt.executeUpdate();
        }
    }

    private static void addHeadphones() throws  SQLException {
        String[] headphones ={"a", "b", "c", "d", "e", "f", "g", "h", "i", "o"};
        int[] prices = {799, 1199, 699, 479, 999, 329, 499, 499, 599, 379};

        for (int i = 0; i < headphones.length; i++) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO headphones(name, price) VALUES (?,?)");
            stmt.setString(1, headphones[i]);
            stmt.setInt(2, prices[i]);
            stmt.executeUpdate();
        }
    }
}
