// Viewmodel yang digunakan untuk pemrosesan game, berupa rendering dan move dari tiap isi game
package viewmodel;

// Import model dan view
import model.Obstacle;
import model.Player;
import view.UpDown;

// Library yang digunakan
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Game implements ActionListener {
    private UpDown UpDown; // View UpDown yang menampilkan game nya

    private int playerStartPosX = 360 / 8; // Atur posisi X mulai si player
    private int playerStartPosY = 640 / 2; // Atur posisi Y mulai si player
    private int playerWidth = 50; // Lebar dari pemain
    private int playerHeight = 68; // Tinggi dari pemain
    private Player player; // Model yang menggambarkan si karakter
    private Timer gameLoop; // Membuat gameloop yang memperbarui dan merender permainan
    private int gravity = 1; // Mengatur berat atau gravitasi karakter saat lompat / sedang di udara
    private boolean isGameOver = false; // Mengatur gameover (awal di set false)
    private int score = 0; // Skor jumlah
    private int scoreUp = 0; // Skor gantungan
    private int scoreDown = 0; // Skor pijakan

    private int bridgePosX; // Mengatur posisi x jembatan 
    private int bridgePosY; // Mengatur posisi y jembatan
    private int bridgeWidth; // Mengatur lebar jembatan
    private int bridgeHeight; // Mengatur tinggi jembatan
    private Image bridgeImage; // Mengisi gambar jembatan
    private boolean playerOnBridge; // Flag apakah player sedang di jembatan atau tidak

    private int bridgeSpeed = -3; // Mengatur kecepatan geser si jembatan
    private boolean startMovingBridge = false; // Mengatur kapan jembatan akan digeser
    private Clip backgroundMusic; // Clip untuk musik latar belakang
    private ManageUsers prosesUsers; // Viewmodel yang digunakan untuk memproses data users

    public Game(UpDown UpDown) throws SQLException {
        this.UpDown = UpDown; // Inisiasi view updown
        this.prosesUsers = new ManageUsers(); // Inisiasi viewmodel ManageUsers
        prosesUsers.getUserPlay(UpDown.getUsername()); // Mengambil data berdasarkan username yang di kirim dari menu
        score = prosesUsers.getUser().getScore(); // Mengambil skor berdasarkan data sebelumnya
        scoreUp = prosesUsers.getUser().getUp(); // Mengambil skor up berdasarkan data sebelumnya
        scoreDown = prosesUsers.getUser().getDown(); // Mengambil skor down berdasarkan data sebelumnya

        // Mengatur karakter yang akan digunakan di dalam permainan 
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, UpDown.getIdleImage());

        // Inisialisasi jembatan
        bridgeWidth = 1080; // atau ukuran yang diinginkan
        bridgeHeight = 32; // atau ukuran yang diinginkan
        bridgePosX = playerStartPosX - 50; // Mengatur posisi x si jembatan 
        bridgePosY = 608 - bridgeHeight; // Mengatur posisi y si jembatan
        bridgeImage = UpDown.getBridgeImage(); // Mengambil gambar dari si view updown
        playerOnBridge = true; // Turn flag as true

        gameLoop = new Timer(1000 / 70, this); // Mengatur interval waktu si Permainan
        gameLoop.start(); // Start gameloopnya

        // Memainkan musik latar belakang
        playBackgroundMusic("../assets/music/love.wav");
    }

    // Prosedur untuk memulai background music
    private void playBackgroundMusic(String filePath) {
        try {
            // Mengambil terlebih dahulu path dari si musiknya
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(filePath));
            
            // Mengambil audionya
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream); // Menyetel audio
            if (isGameOver) {
                backgroundMusic.start();
            } else {
                backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Melakukan loop si audionya
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hentikan background music
    public void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }

    // Proses renderin atau draw image didalam
    public void draw(Graphics g) {
        // Rendering atau draw background image
        g.drawImage(UpDown.getBackgroundImage2(), 0, 0, 1080, 608, null);
        g.drawImage(UpDown.getBackgroundImage3(), 0, 0, 1080, 608, null);
        g.drawImage(UpDown.getBackgroundImage(), 0, 0, 1080, 608, null);

        // Gambar jembatan
        if (bridgePosX + bridgeWidth > 0) { // Hanya gambar jika jembatan masih terlihat di layar
            g.drawImage(bridgeImage, bridgePosX, bridgePosY, bridgeWidth, bridgeHeight, null);
        }

        // Menggambar atau renderin karakter
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        // Menggambar obstacle dan di loop berdasarkan isi dari si ManageObstacles
        for (int i = 0; i < UpDown.getManageObstacle().getObstacles().size(); i++) {
            Obstacle obstacle = UpDown.getManageObstacle().getObstacles().get(i); // Mengambil Obstaclenya

            // Menggambar atau rendering si obstacle
            g.drawImage(obstacle.getImage(), obstacle.getPosX(), obstacle.getPosY(), obstacle.getWidth(), obstacle.getHeight(), null);
            
            // Apabila gantungan, maka gambar juga si tali nya
            if (obstacle.getIsGantungan() == true) {
                g.drawImage(obstacle.getTali(), obstacle.getPosX() + 1, obstacle.getPosY() - 284, obstacle.getWidth() / 5, obstacle.getHeight() * 6, null);
                g.drawImage(obstacle.getTali(), obstacle.getPosX() + 50, obstacle.getPosY() - 284, obstacle.getWidth() / 5, obstacle.getHeight() * 6, null);
            }

            // Gambar crystal hanya jika passed = false
            if (obstacle.getPassed() == false) {
                g.drawImage(obstacle.getCrystal(), obstacle.getPosX() + 1, obstacle.getPosY() - 52, 64, 384 / 8, null);

                // Gambar angka di atas pipa
                g.setColor(Color.white);
                g.setFont(new Font("Arial", Font.PLAIN, 20));
                g.drawString(String.valueOf(obstacle.getScore()), obstacle.getPosX() + obstacle.getWidth() / 2 - 10, obstacle.getPosY() - 62);
            }
        }

        // Gambar atau render text mengenai skor nya
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));

        // Apabila gameover, maka munculkan dialog data dari hasil permainan
        if (isGameOver) {
            JOptionPane.showMessageDialog(null, "Game Over!" + "\nScore: " + score
                    + "\nUp: " + scoreUp + "\nDown: " + scoreDown);

            // Menutup JFrame saat ini dan membuka JFrame sebelumnya
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(UpDown);
            if (currentFrame != null) {
                currentFrame.dispose();
            }
        // Apabila tidak gameover, maka tampilkan datanya di kiri atas
        } else {
            g.drawString("Score: " + String.valueOf((int) score), 10, 35);
            g.drawString("Up: " + String.valueOf((int) scoreUp), 10, 65);
            g.drawString("Down: " + String.valueOf((int) scoreDown), 10, 95);
        }
    }

    // Proses penggerakkan karakter, obstacle, dsb dalam permainan
    public void move() throws SQLException {
        // Mengatur aturan velocity, posisi si karakter
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosX(player.getPosX() + player.getVelocityX());
        player.setPosY(Math.max(player.getPosY(), 0));
        player.setPosX(Math.min(player.getPosX(), UpDown.getFrameWidth() - player.getWidth()));

        // Memeriksa apakah pemain berada di jembatan, kalau iya, maka set true
        if (player.getPosY() + player.getHeight() >= bridgePosY && player.getPosY() <= bridgePosY + bridgeHeight &&
                player.getPosX() + player.getWidth() >= bridgePosX && player.getPosX() <= bridgePosX + bridgeWidth) {
            player.setPosY(bridgePosY - player.getHeight());
            player.setVelocityY(0);
            playerOnBridge = true;
            player.setNullCountJump(bridgeHeight); // Untuk mereset count jump
        } else { // Kalau tidak, maka set false
            playerOnBridge = false;
        }

        // Perulangan untuk menggerakkan si obstacle dalam permainan
        for (int i = 0; i < UpDown.getManageObstacle().getObstacles().size(); i++) {
            Obstacle obstacle = UpDown.getManageObstacle().getObstacles().get(i); // Ambil obstacle
            obstacle.setPosX(obstacle.getPosX() + obstacle.getVelocityX()); // Ubah posisi x nya berdasarkan kecepatannya

            // Memeriksa apakah pemain berada di atas obstacle
            if (player.getPosY() + player.getHeight() >= obstacle.getPosY() && player.getPosY() + player.getHeight() <= obstacle.getPosY() + 5 && player.getPosX() + player.getWidth() >= obstacle.getPosX() && player.getPosX() <= obstacle.getPosX() + obstacle.getWidth()) {
                // Apakah pemain belum pernah menginjak obstacle tersebut, maka tambahkan skor
                if (!obstacle.getPassed()) {
                    obstacle.setPassed(true);
                    if (obstacle.getIsGantungan() == true) {
                        scoreUp = scoreUp + 1;
                    } else {
                        scoreDown = scoreDown + 1;
                    }
                    score += obstacle.getScore(); // Tambahkan nilai poin yang ada di pipa
                }
                // Set null kembali apabila player sudah di tanah ataupun pijakan
                player.setNullCountJump(bridgeHeight);
            }

            // Memeriksa apakah sudah muncul obstacle, kalau sudah maka gerakkan si jembatannya
            if (obstacle.getPosX() < UpDown.getFrameWidth()) {
                startMovingBridge = true;
            }

            // Cek collision, untuk aturan dari si permainannya
            UpDown.getCollision().collide(player, obstacle);
        }

        // Geser jembatan secara bertahap ke kiri
        if (startMovingBridge) {
            bridgePosX += bridgeSpeed; // Geser jembatan ke kiri
        }

        // Apabila karakternya jatuh ataupun ke sebelah kiri, maka hentikan musik dan game over
        if (player.getPosY() > UpDown.getFrameHeight() || player.getPosX() < 0) {
            stopBackgroundMusic(); // Berhenti memutar musik jika game over
            isGameOver = true;
        }

        // Apabila gameover, maka update score
        if (isGameOver) {
            prosesUsers.updateScore(prosesUsers.getUser().getusername(), score, scoreUp, scoreDown);
            playBackgroundMusic("../assets/music/GameOver.wav");
        }

        // Apabila sedang lompat, maka set animasi sedang di udara
        if (player.getCountJump() > 0) {
            player.setImage(UpDown.getAirImage());
            UpDown.repaint();
        }
    }

    // Action performed yang akan dilakukan atau digerakkan saat dipicu
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            move(); // Maka gerakkan 
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        UpDown.repaint(); // Jangan lupa repaint atau merender si tampilan
        
        // Apabila game over, maka stop gameloopnya dan obstaclenya stop untuk di produksi lagi
        if (isGameOver) {
            gameLoop.stop();
            UpDown.getManageObstacle().getObstaclesCooldown().stop();
        }
    }

    // Fungsi untuk mengembalikan si karakter
    public Player getPlayer() {
        return player;
    }

    // Fungsi untuk mengecek game over
    public boolean getIsGameOver() {
        return isGameOver;
    }

    // PRosedur untuk mengatur game over
    public void setIsGameOver() {
        isGameOver = true;
        stopBackgroundMusic();
    }
}
