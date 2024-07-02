// Class yang merupakan Player (Karakter) atau MC yang akan digunakan didalam game nanti

package model;
import java.awt.*;

public class Player {
    private int posX; // Merupakan posisi X karakter
    private int posY; // Merupakan posisi Y karakter
    private int width; // Merupakan lebar karakter
    private int height; // Merupakan tingi karakter
    private Image image; // Merupakan gambar atau penampakan dari si karakter
    private int velocityY; // Menandakan kecepatan bergeser dari si karakter ini secara vertikal
    private int velocityX; // Menandakan kecepatan bergeser dari si karakter ini secara horizontal
    private int countJump; // Menandakan jumlah lompatan si player sudah berapa kali

    // Konstruktor
    public Player(int posX, int posY, int width, int height, Image image) {
        this.posX = posX; // Menandai posisi x karakter
        this.posY = posY; // Menandai posisi y karakter
        this.width = width; // Menandai lebar karakter 
        this.height = height; // Menandai tinggi karakter
        this.image = image; // Menaruh gambar si karakter

        this.velocityY = -6; // Mengatur kecepatan jatuh si karakter secara vertikal
        this.velocityX = 0; // Mengatur kecepatan bergeser karakter secara horizontal (dikarenakan masih awal, jadi 0)
        this.countJump = 0; // Mengatur jumlah lompat karakter di awal game (masih 0)
    }

    /* 
     * 
     *  MERUPAKAN SETTER GETTER DARI CLASS OBSTACLE INI
     * 
     */

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getVelocityX() { return velocityX; }

    public void setVelocityX(int velocityX) { this.velocityX = velocityX; }

    public int getCountJump() { return countJump; }

    public void setNullCountJump(int CountJump) { this.countJump = 0; }
    public void addCountJump() { this.countJump = this.countJump + 1; }
}
