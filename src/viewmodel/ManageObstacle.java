// Viewmodel yang digunakan untuk mengatur atau memproduksi obstacle dalam game nanti
package viewmodel;

// Import model dan view yang digunakan
import model.Obstacle;
import view.UpDown;

// Import library
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ManageObstacle {
    private UpDown adventurer; // Membawa view Game UpDown
    private int obstacleStartPosX = 1080; // Posisi X umum si Obstacle muncul
    private int obstacleStartPosY = 0; // Posisi Y umum si obstacle muncul
    private int obstacleWidth = 64; // Lebar umum dari si obstacle
    private int obstacleHeight = 384; // Tinggi umum dari si obstacle
    private ArrayList<Obstacle> obstacles; // List dari obstacle yang sudah digenerate
    private Timer obstaclesCooldown; // Cooldown dari munculnya si obstacle

    // Konstruktor
    public ManageObstacle(UpDown UpDown) {
        this.adventurer = UpDown; // Inisiasi view dari yang sudah dibawa

        obstacles = new ArrayList<Obstacle>(); // Inisiasi array untuk menampung obstacle

        // COoldown dari renderin si munculnya obstacle
        obstaclesCooldown = new Timer(2000, new ActionListener() {
            @Override
            // Apabila sudah waktunya render, maka ditaruh si obstaclenya
            public void actionPerformed(ActionEvent e) {
                placeObstacles();
            }
        });
        // Mulai timer si render obstacle
        obstaclesCooldown.start();
    }

    // Prosedur untuk menaruh obstacle
    public void placeObstacles() {
        // Membuat instance dari kelas Random
        Random random = new Random();

        // Menghasilkan indeks acak antara 0 dan 4
        int randomGantungan = random.nextInt(5);
        int randomPijakan = random.nextInt(5);

        // Inisiasi posisi X dan Y dari tiap gantungan dan pijakan
        int[] posYGantungan = {100, 140, 180, 220, 260};
        int[] posYPijakan = {548, 498, 448, 398, 348};

        // Inisiasi skor dari tiap kesulitan
        int[] skor = {50, 40, 30, 20, 10};

        // Inisiasi gantungan, lalu tambahkan
        Obstacle gantungan = new Obstacle(obstacleStartPosX + (obstacleStartPosX / 6), posYGantungan[randomGantungan], obstacleWidth, obstacleHeight / 8, adventurer.getGantunganImage(), skor[randomGantungan], adventurer.getBlueCrystalImage(), adventurer.getTaliGantunganImage(), true);
        obstacles.add(gantungan);

        // Inisiasi pijakan, lalu tambahkan
        Obstacle pijakan = new Obstacle(obstacleStartPosX, posYPijakan[randomPijakan], obstacleWidth, obstacleHeight, adventurer.getPijakanImage(), skor[randomPijakan], adventurer.getRedCrystalImage(), adventurer.getTaliGantunganImage(), false);
        obstacles.add(pijakan);
    }

    // Fungsi untuk mengambil list obstacle yang sudah di render
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    // Fungsi untuk mengembalikan cooldown dari munculnya obstacle
    public Timer getObstaclesCooldown() {
        return obstaclesCooldown;
    }
}
