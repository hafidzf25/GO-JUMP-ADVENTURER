// View yang dimana menampilkan si game tersebut
package view;

// Mengimport semua viewmodel
import viewmodel.*;

// Libtrary yang digunakan
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UpDown extends JPanel {
    private Game game; // Merupakan viewmodel yang berisikan proses dalam game tersebut
    private ManageObstacle manageObstacle; // View model yang berisikan pengaturan obstacle yang akan muncul dalam game
    private Input input; // View model yang berisikan aturan input dalam game nanti
    private Collision collision; // View model yang berisikan aturan karakter beserta benda benda maupun sekitar

    private int frameWidth = 1080; // Size lebar dari frame
    private int frameHeight = 608; // Size tinggi dairi frame

    // Background image yang digunakan
    private Image backgroundImage1;
    private Image backgroundImage2;
    private Image backgroundImage3;

    // Image pada karakter
    private Image idleImage; // Ketika diam
    private Image characterImage; // Ketika baru mulai
    private Image jumpImage; // Ketika lompat
    private Image backImage; // Ketika lari kebelakang
    private Image airImage; // Ketika di udara
    
    // Gambar printilan atau benda benda disekitar
    private Image bridgeImage;  // Gambar jembatan sebelum muncul obstacle 
    private Image pijakanImage; // Gambar pijakan
    private Image taliGantunganImage; // Gambar tali gantungan
    private Image gantunganImage; // Gambar gantungan
    private Image crystalBiruImage; // Gambar crystal biru
    private Image crystalMerahImage; // Gambar crystal merah

    private MainMenu menu; // View dari menu
    private ManageUsers prosesUsers; // Viewmodel yang memproses data users

    public UpDown(ManageUsers prosesUsers) throws SQLException {
        this.menu = new MainMenu(); // Inisiasi main menu
        this.prosesUsers = prosesUsers; // Inisiasi viewmodel prosesUsers dari si Menu (membawakan data users yang dimainkan)

        setPreferredSize(new Dimension(frameWidth, frameHeight)); // Mengatur size frame
        setFocusable(true); // Memberikan fokus pada frame ini

        // Penetapan gambar background dari assets
        backgroundImage1 = new ImageIcon(getClass().getResource("../assets/background/plx (1).png")).getImage();
        backgroundImage2 = new ImageIcon(getClass().getResource("../assets/background/plx (2).png")).getImage();
        backgroundImage3 = new ImageIcon(getClass().getResource("../assets/background/plx (3).png")).getImage();

        // Penetapan gambar karakter dan jembatan dari assets
        idleImage = new ImageIcon(getClass().getResource("../assets/character/idle outline.gif")).getImage();
        characterImage = new ImageIcon(getClass().getResource("../assets/character/run outline.gif")).getImage();
        backImage = new ImageIcon(getClass().getResource("../assets/character/back outline.gif")).getImage();
        airImage = new ImageIcon(getClass().getResource("../assets/character/mid air outline.gif")).getImage();
        jumpImage = new ImageIcon(getClass().getResource("../assets/character/jump outline.png")).getImage();
        bridgeImage = new ImageIcon(getClass().getResource("../assets/perintilan/landing.png")).getImage();

        // Penetapan gambar obstacle dari assets
        taliGantunganImage = new ImageIcon(getClass().getResource("../assets/perintilan/tali_gantungan.png")).getImage();
        pijakanImage = new ImageIcon(getClass().getResource("../assets/perintilan/pijakan.png")).getImage();
        gantunganImage = new ImageIcon(getClass().getResource("../assets/perintilan/gantungan.png")).getImage();

        // Penetapan gambar crystal dari assets
        crystalBiruImage = new ImageIcon(getClass().getResource("../assets/perintilan/blue_crystal.gif")).getImage();
        crystalMerahImage = new ImageIcon(getClass().getResource("../assets/perintilan/red_crystal.gif")).getImage();

        // Inisiasi viewmodel yang akan berperan dalam game
        game = new Game(this); // Pemrosesan dari isi game
        manageObstacle = new ManageObstacle(this); // Pemrosesan obstacle yang akan di munculkan
        input = new Input(this); // Pemrosesan aturan input dalam game
        collision = new Collision(this); // Pemrosesan aturan karakter dalam game nanti
    }

    // Pemrosesan untuk menggambar komponen permainan
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw(g);

        // Apabila game over, maka dialihkan ke menu awal
        if (game.getIsGameOver() == true) {
            menu.main(null);
        }
    }

    /*
     * 
     * Getter dan setter dari view
     * 
     */

    public int getFrameWidth() { return frameWidth; }
    public int getFrameHeight() { return frameHeight; }
    public Game getGame() { return game; }
    public ManageObstacle getManageObstacle() { return manageObstacle; }
    public Collision getCollision() { return collision; }
    public Image getBackgroundImage() { return backgroundImage1; }
    public Image getBackgroundImage2() { return backgroundImage2; }
    public Image getBackgroundImage3() { return backgroundImage3; }
    public Image getIdleImage() { return idleImage; }
    public Image getAirImage() { return airImage; }
    public Image getCharacterImage() { return characterImage; }
    public Image getBackImage() { return backImage; }
    public Image getJumpImage() { return jumpImage; }
    public Image getBridgeImage() { return bridgeImage; }
    public Image getPijakanImage() { return pijakanImage; }
    public Image getBlueCrystalImage() { return crystalBiruImage; }
    public Image getRedCrystalImage() { return crystalMerahImage; }
    public Image getGantunganImage() { return gantunganImage; }
    public Image getTaliGantunganImage() { return taliGantunganImage; }
    public String getUsername() { return prosesUsers.getUsername(); }
}
