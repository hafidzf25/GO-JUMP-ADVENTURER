// Class atau model yang dimana berfungsi untuk mencari data users pada database

package model;

import java.sql.*;

public class TableUser {
    private Database db; // Inisiasi database yang digunakan

    // Konstruktor
    public TableUser() {
        this.db = new Database();
    }

    // Fungsi untuk melakukan select semua data/record dalam table tscore
    public ResultSet getTusers() throws SQLException {
        String query = "SELECT * FROM tscore";
        return db.executeQuery(query);
    }

    // Fungsi untuk mengambil data/record dalam table berdasarkan syarat tertentu
    // (yaitu username)
    public User getTusersByName(String searchUsername) throws SQLException {
        String query = "SELECT * FROM tscore WHERE username = ?";
        PreparedStatement preparedStatement = db.prepareStatement(query); // Menaruh query pada statement
        preparedStatement.setString(1, searchUsername); // Mengisi value ? pada statement
        ResultSet resultSet = preparedStatement.executeQuery(); // Mengeksekusi query dan menghasilkan data

        // Apabila ada datanya
        if (resultSet.next()) {
            String username = resultSet.getString("username"); // Mengambil username
            int score = resultSet.getInt("score"); // Mengambil score
            int up = resultSet.getInt("up"); // Mengambil jumlah skor gantungan
            int down = resultSet.getInt("down"); // Mengambil jumlah skor pijakan
            
            // Mengembalikan data berupa Model User
            return new User(username, score, up, down);
        } else { // Apabila tidak ada
            return null; // kembalikan null
        }
    }

    // Fungsi untuk melakukan penambahan User (apabila username yang di input tidak ada dalam data)
    public boolean addUser(String searchUsername) throws SQLException {
        // Query untuk insert data
        String query = "INSERT INTO `tscore`(`username`, `score`, `up`, `down`) VALUES (?, ?, ?, ?)";

        // Persiapan statement dan mengisi ?
        PreparedStatement stmt = db.prepareStatement(query);
        stmt.setString(1, searchUsername);
        stmt.setInt(2, 0);
        stmt.setInt(3, 0);
        stmt.setInt(4, 0);

        // Eksekusi query
        int rowsInserted = stmt.executeUpdate();
        return rowsInserted > 0;
    }

    // Fungsi untuk melakukan update User (apabila sudah selesai game)
    public boolean updateUser(String username, int score, int up, int down) throws SQLException {
        // Query untuk update data
        String query = "UPDATE tscore SET score = ?, up = ?, down = ? WHERE username = ?";

        // Persiapan statement
        PreparedStatement stmt = db.prepareStatement(query);
        stmt.setInt(1, score);
        stmt.setInt(2, up);
        stmt.setInt(3, down);
        stmt.setString(4, username);

        // Eksekusi query
        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;
    }
}
