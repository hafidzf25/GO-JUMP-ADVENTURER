// Viewmodel untuk aturan input dalam permainan
package viewmodel;

// Memanggil view game
import view.UpDown;

// Library
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Input implements KeyListener {
    private UpDown UpDown; // Inisiasi view game updown

    public Input(UpDown UpDown) {
        this.UpDown = UpDown;

        // Fungsi untuk menambahkan trigger apabila terjadi pencet/input dalam keyboard
        UpDown.addKeyListener(this);
    }

    // Apabila diketik
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Apabila ditekan salah satu inputan atau keyboard
    @Override
    public void keyPressed(KeyEvent e) {
        // Apabila ditekan tombol panah UP, maka akan loncat
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            // Apabila masih dibawah 2 kali loncat, maka bisa loncat
            if (UpDown.getGame().getPlayer().getCountJump() < 2) {
                playBackgroundMusic("../assets/music/Jump.wav");
                UpDown.getGame().getPlayer().addCountJump();
                UpDown.getGame().getPlayer().setImage(UpDown.getJumpImage());
                UpDown.getGame().getPlayer().setVelocityY(-20);
                UpDown.repaint();
            }
            // Apabila ditekan tombol panah LEFT atau kiri, maka gerak ke sebelah kiri
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            UpDown.getGame().getPlayer().setImage(UpDown.getBackImage());
            UpDown.getGame().getPlayer().setVelocityX(-6);
            UpDown.repaint();
            // Apabila ditekan tombol panah LEFT atau kiri, maka gerak ke sebelah kanan
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            UpDown.getGame().getPlayer().setImage(UpDown.getCharacterImage());
            UpDown.getGame().getPlayer().setVelocityX(3);
            UpDown.repaint();
            // Apabila ditekan tombol spasi, maka game over
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            UpDown.getGame().setIsGameOver();
        }
    }

    // Apabila setelah dilepas
    @Override
    public void keyReleased(KeyEvent e) {
        // Setelah gerak kiri atau kanan, maka atur velocity menjadi 0 lagi, dan set
        // imagenya menjadi diam
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            UpDown.getGame().getPlayer().setVelocityX(0);
            UpDown.getGame().getPlayer().setImage(UpDown.getIdleImage());
            // Setelah lompat, maka atur kembali menjadi diam
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            UpDown.getGame().getPlayer().setImage(UpDown.getIdleImage());
        }
    }

    // Prosedur untuk memulai background music
    private void playBackgroundMusic(String filePath) {
        try {
            // Mengambil terlebih dahulu path dari si musiknya
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(filePath));
            
            // Mengambil audionya
            Clip backgroundMusic; // Clip untuk musik latar belakang
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream); // Menyetel audio
            backgroundMusic.start(); // Setel sekali
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
