package flyweight;

public class Enemy {
    private int x, y, speed;
    private EnemySprite sprite;

    public Enemy(int x, int y, int speed, EnemySprite sprite) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.sprite = sprite;
    }

    public void move(int panelWidth) {
        x += speed;
        if (x > panelWidth) x = -sprite.getWidth();
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public EnemySprite getSprite() { return sprite; }
}
