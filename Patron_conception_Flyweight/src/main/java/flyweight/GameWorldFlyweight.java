package flyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWorldFlyweight {
    private List<Enemy> enemies = new ArrayList<>();
    private static final String[] TYPES = {"alien", "robot", "zombie"};
    private Random random = new Random();

    private static final int DEFAULT_WORLD_WIDTH = 800;
    private static final int DEFAULT_WORLD_HEIGHT = 600;

    public void generateEnemies(int count) {
        generateEnemies(count, DEFAULT_WORLD_WIDTH, DEFAULT_WORLD_HEIGHT);
    }

    public void generateEnemies(int count, int worldWidth, int worldHeight) {
        int safeWorldWidth = Math.max(1, worldWidth);
        int safeWorldHeight = Math.max(1, worldHeight);

        for (int i = 0; i < count; i++) {
            addRandomEnemy(safeWorldWidth, safeWorldHeight);
        }
    }

    public void addRandomEnemy(int worldWidth, int worldHeight) {
        String type = TYPES[random.nextInt(TYPES.length)];
        EnemySprite sprite = EnemySpriteFactory.getSprite(type);

        int x = random.nextInt(Math.max(1, worldWidth - sprite.getWidth()));
        int y = random.nextInt(Math.max(1, worldHeight - sprite.getHeight()));
        int speed = random.nextInt(5) + 1;

        enemies.add(new Enemy(x, y, speed, sprite));
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void removeLastEnemy() {
        if (!enemies.isEmpty()) enemies.remove(enemies.size() - 1);
    }

    public void modifyRandomEnemy() {
        if (enemies.isEmpty()) return;
        Random r = new Random();
        Enemy e = enemies.get(r.nextInt(enemies.size()));
        e.move(800); // Déplacement aléatoire pour simuler modification
    }
}
