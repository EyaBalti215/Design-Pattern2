package flyweight;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel {
    private List<Enemy> enemies;

    public GamePanel(List<Enemy> enemies) {
        this.enemies = enemies;
        setBackground(Color.BLACK);
    }

    public void updatePositions() {
        for (Enemy e : enemies) e.move(getWidth());
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Enemy e : enemies) g.drawImage(e.getSprite().getImage(), e.getX(), e.getY(), null);
    }
}
