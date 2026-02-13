package flyweight;

import java.awt.image.BufferedImage;

public class EnemySprite {
    private final String type;
    private final BufferedImage image;
    private final int width;
    private final int height;

    public EnemySprite(String type, BufferedImage image) {
        this.type = type;
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public BufferedImage getImage() { return image; }
    public String getType() { return type; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
