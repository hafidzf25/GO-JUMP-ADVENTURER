// Class untuk menyimpan obstacle seperti gantungan dan pijakan yang ada di dalam game

package model;
import java.awt.*;

public class Obstacle {
    private int posX; // Menandakan posisi X si osbtacle
    private int posY; // Menandakan posisi Y si osbtacle
    private int width; // Menandakan lebar si osbtacle
    private int height; // Menandakan tinggi si osbtacle
    private Image tali; // Menyimpan gambar tali si gantungan dalam game
    private Image image; // Menyimpan gambar si obstacle (gantungan / pijakan)
    private Image crystal; // Menyimpan gambar crystal yang merupakan gambaran skor yang harus diambil
    private int velocityX; // Menandakan kecepatan si obstacle ini bergeser 
    private boolean passed; // Menandakan bahwa obstacle ini sudah di pijak atau belum
    private boolean isGantungan; // Menandakan apakah si obstacle ini gantungan atau pijakan
    private int score; // Menandakan score tiap obstacle 

    // Konstruktor
    public Obstacle(int posX, int posY, int width, int height, Image image, int skor, Image crystal, Image tali, boolean isGantungan) {
        this.posX = posX; // Menandai posisi X
        this.posY = posY; // Menandai posisi Y
        this.width = width; // Menaruh lebar obstacle
        this.height = height; // Menaruh tinggi obstacle
        this.image = image; // Menaruh gambar si obstacle

        this.velocityX = -3; // Mengatur kecepatan obstacle bergeser
        this.passed = false; // Diatur false terlebih dahulu, dikarenakan belum di pijak
        this.score = skor; // Menandai skor obstacle
        this.crystal = crystal; // Menaruh gambar crystal
        this.isGantungan = isGantungan; // Menandai apakah obstacle ini gantungan atau pijakan
        this.tali = tali; // Menaruh gambar si tali
    }

    /* 
     * 
     *  MERUPAKAN SETTER GETTER DARI CLASS OBSTACLE INI
     * 
     */

    public int getPosX() { return posX; }

    public void setPosX(int posX) { this.posX = posX; }

    public int getPosY() { return posY; }

    public void setPosY(int posY) { this.posY = posY; }

    public int getWidth() { return width; }

    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    public Image getImage() { return image; }

    public void setImage(Image image) { this.image = image; }

    public Image getTali() { return tali; }

    public Image getCrystal() { return crystal; }

    public boolean getIsGantungan() { return isGantungan; }

    public int getVelocityX() { return velocityX; }

    public void setVelocityX(int velocityX) { this.velocityX = velocityX; }

    public boolean getPassed() { return passed; }

    public void setPassed(boolean passed) { this.passed = passed; }

    public int getScore() { return score; }
}
