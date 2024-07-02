// Class untuk menyimpan database beserta query yang akan digunakannya

package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connection; // Koneksi yang akan digunakan saat execute query yang dibawa
    private Statement statement; // Statement yang merupakan dibawa saat mengeksekusi sebuah query

    // Konstruktor database
    public Database() {
        try { // Membawakan localhost yang dimana terdapat database si tmd ini yaitu db_tmd
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_tmd", "root", "");
            this.statement = this.connection.createStatement();
        } catch (SQLException var2) {
            throw new RuntimeException(var2);
        }
    }

    // Mengembalikan connection yang dibawa
    public Connection getConnection() {
        return connection;
    }

    // Menutup connection
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Membawakan atau mengambil Statement database
    public Statement getStatement() {
        return this.statement;
    }

    // Mengembalikan hasil dari execute query yang dibawa, berupa data
    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    // Mengembalikan pula hasil dari execute yang berupa update, dan dikembalikan data
    public int executeUpdate(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    // Menyiapkan statement yang akan diexecute
    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }
}
