package PaooGame;

import java.awt.*;

public class GameUI {
    private int score;
    private int lives;

    public GameUI() {
        this.score = 0;
        this.lives = 3;
    }

    public void addScore(int points) {
        score += points;
    }

    public void loseLife() {
        lives--;
    }

    public void Draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + score, 20, 20);
        g.drawString("Lives: " + lives, 20, 45);
    }
}

