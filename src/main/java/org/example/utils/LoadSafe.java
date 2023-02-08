package org.example.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Objects;

import static org.example.main.Game.TILES_IN_HEIGHT;
import static org.example.main.Game.TILES_IN_WIDTH;

public class LoadSafe {
    public static BufferedImage getSpriteAtlas(Image image) {
        try (InputStream is = LoadSafe.class.getClassLoader().getResourceAsStream(image.getPath())) {
            return ImageIO.read(Objects.requireNonNull(is));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
            throw new RuntimeException(e);
        }
    }

    public static int[][] getLevelData() {
        int[][] levelData = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
        BufferedImage img = getSpriteAtlas(Image.LEVEL_ONE_DATA);

        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48)
                    value = 0;
                levelData[j][i] = value;
            }
        return levelData;
    }
}
