// Viewmodel untuk mengatur aturan tabrakan atau gameover dari permainan
package viewmodel;

// Model beserta view yang disertakan dalam pemrosesan
import model.Obstacle;
import model.Player;
import view.UpDown;

public class Collision {
    // Inisiasi View Game UpDown
    private UpDown UpDown;

    // Konstruktor
    public Collision(UpDown UpDown) {
        this.UpDown = UpDown;
    }

    // Aturan tabrakan atau game over yang dibuat
    public void collide(Player player, Obstacle obstacle) {
        if (checkCollision(player, obstacle))
        {
            // Jarak sebelah kiri dari si player
            float leftDistance = Math.abs((player.getPosX() + player.getWidth()) - obstacle.getPosX());

            // Jarak sebelah kanan dari si player
            float rightDistance = Math.abs(player.getPosX() - (obstacle.getPosX() + obstacle.getWidth()));

            // Jarak bawah dari si player
            float downDistance = Math.abs((player.getPosY() + player.getHeight()) - obstacle.getPosY());

            // Jarak atas dari si player
            float upDistance = Math.abs(player.getPosY() - (obstacle.getPosY() + obstacle.getHeight()));
            
            // Mengatur aturan agar karakter tidak bisa menembus dari sisi kiri dari obstaclenya
            if (leftDistance < rightDistance && leftDistance < downDistance && leftDistance < upDistance) {
                player.setVelocityX(-3);
                player.setPosX(obstacle.getPosX() - player.getWidth());
            }

            // Mengatur aturan agar karakter tidak bisa menembus dari sisi kanan dari obstaclenya
            else if (rightDistance < downDistance && rightDistance < upDistance) {
                player.setVelocityX(0);
                player.setPosX(obstacle.getPosX() + obstacle.getWidth());
            }

            // Mengatur aturan agar karakter bisa berdiri di atas obstacle nya
            else if (downDistance < upDistance) {
                player.setVelocityY(0);
                player.setPosY(obstacle.getPosY() - player.getHeight());
            }

            // Mengatur aturan agar karakter tidak bisa menembus dari bawah ke atas obstacle
            else {
                player.setVelocityY(0);
                player.setPosY(obstacle.getPosY() + obstacle.getHeight());
            }
        }
    }

    // Mengecek collision
    private boolean checkCollision(Player player, Obstacle obstacle) {
        return  player.getPosX() < obstacle.getPosX() + obstacle.getWidth() &&
                player.getPosX() + player.getWidth() > obstacle.getPosX() &&
                player.getPosY() < obstacle.getPosY() + obstacle.getHeight() &&
                player.getPosY() + player.getHeight() > obstacle.getPosY();
    }
}
