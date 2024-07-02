// Class atau model untuk User yang akan dimainkan saat permainan

package model;

public class User {
    private String username; // Merupakan username si user
    private int score; // Merupakan jumlah skor si user
    private int up; // Merupakan jumlah gantungan yang sudah di injak user
    private int down; // Merpakan jumlah pijakan yang sudah di injak user

    // Konstruktor
    public User(String username, int score, int up, int down) {
        this.username = username; // Menaruh username
        this.score = score; // Menaruh skornya
        this.up = up; // Menaruh jumlah gantungan yg sudah di injak
        this.down = down; // Menaruh jumlah pijakan yang sudah di injak
    }

    /* 
     * 
     *  MERUPAKAN SETTER GETTER DARI CLASS OBSTACLE INI
     * 
     */

    public void setusername(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public void setDown(int down) {
        this.down = down;
    }
    
    public String getusername() {
        return this.username;
    }

    public int getScore() {
        return score;
    }

    public int getUp() {
        return up;
    }
    
    public int getDown() {
        return down;
    }
}
