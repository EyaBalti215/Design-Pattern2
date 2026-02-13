package flyweight;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EnemySpriteFactory {
    private static final Map<String, EnemySprite> sprites = new HashMap<>();

    public static EnemySprite getSprite(String type) {
        if (!sprites.containsKey(type)) {
            BufferedImage image;
            try {
                InputStream is = EnemySpriteFactory.class.getClassLoader().getResourceAsStream(type + ".png");
                if (is != null) {
                    image = ImageIO.read(is);
                    System.out.println("Sprite chargé : " + type);
                } else {
                    System.err.println("Sprite manquant : " + type + ". Création d'un sprite coloré.");
                    image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = image.createGraphics();
                    switch (type) {
                        case "alien": g2d.setColor(Color.GREEN); break;
                        case "robot": g2d.setColor(Color.BLUE); break;
                        case "zombie": g2d.setColor(Color.RED); break;
                        default: g2d.setColor(Color.YELLOW);
                    }
                    g2d.fillRect(0, 0, 50, 50);
                    g2d.dispose();
                }
            } catch (Exception e) {
                e.printStackTrace();
                image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = image.createGraphics();
                g2d.setColor(Color.MAGENTA);
                g2d.fillRect(0, 0, 50, 50);
                g2d.dispose();
            }
            sprites.put(type, new EnemySprite(type, image));
        }
        return sprites.get(type);
    }

    public static int getSpriteCount() { return sprites.size(); }
}
