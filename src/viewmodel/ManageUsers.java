// Viewmodel untuk mengatur atau mengolah data si users
package viewmodel;

// Library
import java.sql.ResultSet;
import java.sql.SQLException;

// Import model yang akan digunakan
import model.TableUser;
import model.User;

public class ManageUsers {
    private TableUser tabeluser; // digunakan untuk mengakses data database
	private User currentUser; // tanda si user yang sedang digunakan sekarang

    // Konstruktor
    public ManageUsers() {
        this.tabeluser = new TableUser();
    }

    // Fungsi untuk mengembalikan semua users 
    public ResultSet getAllUsers() throws SQLException {
        return tabeluser.getTusers();
    }

    // Prosedur untuk mencari users yang dipilih untuk dimainkan
    public void getUserPlay(String username) throws SQLException {
        currentUser = tabeluser.getTusersByName(username); // Mencari berdasarkan nama
        if (currentUser == null) { // Apabila tidak ditemukan, maka add dan ambil lagi
            tabeluser.addUser(username);
            currentUser = tabeluser.getTusersByName(username);
        }
    }

    // Mengembalikan username si users
    public String getUsername() {
        return currentUser.getusername();
    }

    // Mengembalikan User
    public User getUser() {
        return this.currentUser;
    }

    // Prosedur untuk update skor si user
    public void updateScore(String username, int score, int up, int down) throws SQLException {
        tabeluser.updateUser(username, score, up, down);
    }
}
